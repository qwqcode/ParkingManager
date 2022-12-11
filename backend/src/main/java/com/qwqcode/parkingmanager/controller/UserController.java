package com.qwqcode.parkingmanager.controller;

import com.qwqcode.parkingmanager.entity.*;
import com.qwqcode.parkingmanager.model.req.*;
import com.qwqcode.parkingmanager.model.res.*;
import com.qwqcode.parkingmanager.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private RecService recService;

    @Autowired
    private TicketService ticketService;

    /**
     * 用户登录
     */
    @PostMapping("/api/login")
    public CommonResp userLogin(UserLoginParams params, HttpServletRequest req) {
        User user = userService.checkGetUser(params.getPhone(), params.getPassword());

        if (user == null) {
            return CommonResp.Error("登录失败");
        }

        // 查询会员卡
        VipCard vipCard = userService.findVipCard(user.getId());

        // 设置 Session
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        // 响应数据
        UserLoginResp resp = new UserLoginResp();
        resp.setUser(user);
        resp.setVip_card(vipCard);

        return CommonResp.Data(resp);
    }

    /**
     * 用户注册
     */
    @PostMapping("/api/signup")
    public CommonResp userSignup(UserSignupParams params, HttpServletRequest req) {
        boolean result = userService.signupUser(params.getPhone(), params.getPassword());
        if (result) return CommonResp.Success("注册成功");
        else return CommonResp.Error("注册失败");
    }

    /**
     * 用户注销
     */
    @PostMapping("/api/user/logout")
    public CommonResp userLogout(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("user");
        return CommonResp.Success("注销成功");
    }

    /**
     * 用户当前信息
     */
    @PostMapping("/api/user/me")
    public CommonResp userInfo(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = userService.getUserByID(((User)session.getAttribute("user")).getId());

        return CommonResp.Data(user);
    }

    /**
     * 用户车辆绑定
     */
    @PostMapping("/api/user/bind-car")
    public CommonResp bindCar(HttpServletRequest req, UserBindCarParams params) {
        int userID = ((User)req.getSession().getAttribute("user")).getId();

        // 查询会员卡
        VipCard vipCard = userService.findVipCard(userID);
        if (vipCard == null) {
            return CommonResp.Error("只有 VIP 用户才能绑定车辆");
        }
        int vipType = vipCard.getType();

        // 查询用户已绑定车辆
        List<Car> carList = carService.findUserCars(userID);

        // 普卡 3 辆、金卡 5 辆
        if (vipType == VipCard.TYPE_NORMAL_VIP && carList.size() >= 3) {
            return CommonResp.Error("普卡 VIP 最多只能绑定 3 辆车");
        } else if (vipType == VipCard.TYPE_GOLD_VIP && carList.size() >= 5) {
            return CommonResp.Error("金卡 VIP 最多只能绑定 5 辆车");
        }

        // 查询车辆是否存在于数据库中
        Car car = carService.findCarByInfo(params.getCar_plate());
        if (car == null) {
            // 创建车辆：新增模式
            car = new Car();
            car.setPlate(params.getCar_plate());
            car.setPlate_type(params.getCar_plate_type());
            car.setUser_id(userID); // 用户 ID
            if (!carService.createCar(car)) {
                return CommonResp.Error("车辆创建失败");
            }
            return CommonResp.Success("车辆绑定成功");
        } else {
            // 编辑模式
            if (car.getUser_id() != 0) {
                return CommonResp.Error(car.getUser_id() == userID ? "该用户已绑定过该车辆" : "其他用户已绑定该车辆");
            }

            car.setUser_id(userID);
            if (carService.editCarUserID(car.getId(), car.getUser_id())) {
                return CommonResp.Success("车辆绑定成功");
            } else  {
                return CommonResp.Error("车辆绑定失败");
            }
        }
    }

    /**
     * 用户车辆查询
     */
    @PostMapping("/api/user/cars")
    public CommonResp userCars(HttpServletRequest req) {
        int userID = ((User)req.getSession().getAttribute("user")).getId();

        // 用户绑定车辆不会太多，不用做分页
        List<Car> carList = carService.findUserCars(userID);

        // 查询停车记录
        for (Car car : carList) {
            car.setRecs(recService.findCarRecs(car.getId()));
        }

        UserCarsResp resp = new UserCarsResp();
        resp.setCars(carList);

        return CommonResp.Data(resp);
    }

    /**
     * 车辆信息查询
     */
    @PostMapping("/api/user/car-query")
    public CommonResp carQuery(HttpServletRequest req, UserCarQueryParams params) {
        Car car;
        if (params.getCar_plate() != null && !params.getCar_plate().equals("")) {
            car = carService.findCarByInfo(params.getCar_plate());
        } else {
            car = carService.findCarByID(params.getCar_id());
        }

        if (car == null) {
            return CommonResp.Error("无车辆数据");
        }

        // 查询停车记录
        car.setRecs(recService.findCarRecs(car.getId()));

        CarQueryResp resp = new CarQueryResp();
        resp.setCar(car);

        return CommonResp.Data(resp);
    }

    /**
     * 指定车辆停车记录查询
     */
    @PostMapping("/api/user/car-recs")
    public CommonResp carRecs(HttpServletRequest req, UserCarRecsParams params) {
        List<Rec> recList = recService.findCarRecsPagination(params.getCar_id(), params.getOffset(), params.getLimit());
        int total = recService.countCarRecs(params.getCar_id());

        CarRecsResp resp = new CarRecsResp();
        resp.setRecs(recList);
        resp.setTotal(total);

        return CommonResp.Data(resp);
    }

    /**
     * 用户停车缴费
     */
    @PostMapping("/api/user/rec-pay")
    public CommonResp recPay(HttpServletRequest req, UserRecPayParams params) {
        Rec rec = recService.findRecByID(params.getRec_id());
        if (rec == null) {
            return CommonResp.Error("找不到停车记录");
        }

        // 计算需支付金额
        BigDecimal price;

        // 小票抵扣
        int use_ticket_id = 0;
        int ticket_hours_de = 0; // 小票小时减免
        if (params.getTicket_key() != null && !params.getTicket_key().equals("")) {
            Ticket ticket = ticketService.findTicketByKey(params.getTicket_key());
            if (ticket == null || ticket.getIs_available() == 0) {
                return CommonResp.Error("小票无效");
            }
            use_ticket_id = ticket.getId();

            // 查询小票预设
            TicketPreset tPreset = ticketService.findTicketPresetByID(ticket.getPreset_id());
            if (tPreset == null || tPreset.getIs_available() == 0) {
                return CommonResp.Error("小票预设无效");
            }
            if (tPreset.getAct().equals("hours-de")) { // 小票预设类型：小时减免
                ticket_hours_de = Integer.parseInt(tPreset.getAct_val());
            }
        }

        // 停车时长计算
        long minutes = rec.getParking_time();

        price = rec.getParking_price(ticket_hours_de);

        // 接口功能：仅计算价格而不执行支付
        if (params.getIs_challenge() == 1) {
            return CommonResp.Data(price);
        }

        // 用户代金劵 (TODO)
        int use_coupon_id = 0;

        // 用户余额支付
        int is_use_vip_card = 0;

        // 支付记录新增
        RecPay pay = new RecPay();

        pay.setCar_id(rec.getCar_id());
        pay.setRec_id(rec.getId());
        pay.setPark_id(rec.getPark_id());
        pay.setPrice(price);
        pay.setUse_ticket_id(use_ticket_id);
        pay.setUse_coupon_id(use_coupon_id);
        pay.setIs_use_vip_card(is_use_vip_card);

        if (!recService.createRecPay(pay)) {
            return CommonResp.Error("支付失败");
        }

        if (recService.updateRecPayID(rec, pay)) {
            return CommonResp.Success("支付成功");
        } else {
            return CommonResp.Error("缴费成功，但是缴费状态修改失败");
        }
    }
}
