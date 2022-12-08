package com.qwqcode.parkingmanager.controller;

import com.qwqcode.parkingmanager.common.Utils;
import com.qwqcode.parkingmanager.entity.Car;
import com.qwqcode.parkingmanager.entity.Park;
import com.qwqcode.parkingmanager.entity.Rec;
import com.qwqcode.parkingmanager.model.req.CarInParams;
import com.qwqcode.parkingmanager.model.req.CarOutParams;
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

    @PostMapping("/api/internal/car-in")
    public CommonResp carIn(CarInParams params) {
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

        // 查询是否存在激活的停车记录
        Rec existRec = recService.findLatestRecByCarID(car.getId());
        if (existRec != null) {
            return CommonResp.Error("车辆当前已处于停泊状态，无法多次进入停车场");
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

    @PostMapping("/api/internal/car-out")
    public CommonResp carOut(CarOutParams params) {
        // 查询车辆
        Car car = carService.findCarByInfo(params.getCar_plate());
        if (car == null) {
            return CommonResp.Error("未找到车辆");
        }

        // 查找停车记录
        Rec rec = recService.findLatestRecByCarID(car.getId());
        if (rec == null) {
            return CommonResp.Error("停车记录不存在");
        }

        if (recService.updateRecOutTime(rec)) {
            return CommonResp.Data(rec);
        } else {
            return CommonResp.Error("停车记录更新失败");
        }
    }
}
