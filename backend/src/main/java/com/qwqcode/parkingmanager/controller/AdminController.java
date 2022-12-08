package com.qwqcode.parkingmanager.controller;

import com.qwqcode.parkingmanager.model.req.*;
import com.qwqcode.parkingmanager.model.res.*;
import com.qwqcode.parkingmanager.service.*;
import com.qwqcode.parkingmanager.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private CarService carService;

    @Autowired
    private RecService recService;

    @PostMapping("/api/admin/cars")
    public CommonResp adminCars(AdminCarParams params) {
        List<Car> carList = carService.findAllCars(params.getOffset(), params.getLimit());
        int total = carService.countAllCars();

        AdminCarsResp resp = new AdminCarsResp();
        resp.setCars(carList);
        resp.setTotal(total);

        return CommonResp.Data(resp);
    }

    @PostMapping("/api/admin/recs")
    public CommonResp adminRecs(AdminRecParams params) {
        List<Rec> recList = recService.findAllRecs(params.getOffset(), params.getLimit());
        int total = recService.countAllRecs();

        AdminRecsResp resp = new AdminRecsResp();
        resp.setRecs(recList);
        resp.setTotal(total);

        return CommonResp.Data(resp);
    }
}
