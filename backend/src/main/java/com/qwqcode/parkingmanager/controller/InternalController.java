package com.qwqcode.parkingmanager.controller;

import com.qwqcode.parkingmanager.common.Utils;
import com.qwqcode.parkingmanager.entity.Car;
import com.qwqcode.parkingmanager.entity.Park;
import com.qwqcode.parkingmanager.entity.Rec;
import com.qwqcode.parkingmanager.model.req.RecCreateParams;
import com.qwqcode.parkingmanager.model.req.UserLoginParams;
import com.qwqcode.parkingmanager.model.res.CommonResp;
import com.qwqcode.parkingmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仅内网可访问的接口
 */
@RestController
public class InternalController {
    @Autowired
    private RecService recService;

    @Autowired
    private CarService carService;

    @Autowired
    private ParkService parkService;

    @PostMapping("/api/internal/rec-create")
    private CommonResp recCreate(RecCreateParams params) {
        // 查询停车场
        Park park = parkService.findParkByID(params.getPark_id());
        if (park == null) {
            return CommonResp.Error("未找到停车场");
        }

        // 查询车辆是否存在于数据库中
        Car car = carService.findCarByInfo(params.getCar_plate());
        if (car == null) {
            // 创建车辆
            car = new Car();
            car.setPlate(params.getCar_plate());
            car.setPlate_type(params.getCar_plate_type());
            if (!carService.createCar(car)) {
                return CommonResp.Error("车辆创建失败");
            }
        }

        // 创建停车记录
        Rec rec = new Rec();
        rec.setIn_at(Utils.getNowDate());
        rec.setCar_id(car.getId());
        rec.setPark_id(park.getId());
        if (recService.createRec(rec)) {
            return CommonResp.Data(rec);
        } else {
            return CommonResp.Error("停车记录创建失败");
        }
    }

//    @PostMapping("/api/internal/rec-create")
}
