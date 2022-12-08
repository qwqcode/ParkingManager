package com.qwqcode.parkingmanager.controller;

import com.qwqcode.parkingmanager.entity.*;
import com.qwqcode.parkingmanager.model.req.*;
import com.qwqcode.parkingmanager.model.res.*;
import com.qwqcode.parkingmanager.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private RecService recService;

    @PostMapping("/api/login")
    public CommonResp userLogin(UserLoginParams params, HttpServletRequest req) {
        User user = userService.checkGetUser(params.getPhone(), params.getPassword());

        if (user == null) {
            return CommonResp.Error("登录失败");
        }

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        return CommonResp.Success("登录成功");
    }

    @PostMapping("/api/signup")
    public CommonResp userSignup(UserSignupParams params, HttpServletRequest req) {
        boolean result = userService.signupUser(params.getPhone(), params.getPassword());
        if (result) return CommonResp.Success("注册成功");
        else return CommonResp.Error("注册失败");
    }

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

        boolean isExisted = carList.stream().anyMatch(c -> c.getPlate().equals(params.getCar_plate()));
        if (isExisted) {
            return CommonResp.Error("该用户已绑定过该车辆");
        }

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

        UserCarsResp resp = new UserCarsResp();
        resp.setCars(carList);

        return CommonResp.Data(resp);
    }

    /**
     * 用户停车记录查询
     */
    @PostMapping("/api/user/recs")
    public CommonResp userRecs(HttpServletRequest req, UserRecsParams params) {
        int userID = ((User)req.getSession().getAttribute("user")).getId();

        List<Rec> recList = recService.findUserRecs(userID, params.getOffset(), params.getLimit());
        int total = recService.countUserRecs(userID);

        UserRecsResp resp = new UserRecsResp();
        resp.setRecs(recList);
        resp.setTotal(total);

        return CommonResp.Data(resp);
    }
}
