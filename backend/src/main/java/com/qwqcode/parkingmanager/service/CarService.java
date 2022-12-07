package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Car;
import com.qwqcode.parkingmanager.mapper.CarMapper;
import com.qwqcode.parkingmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;

    /** 新增车辆 */
    public boolean createCar(Car car) {
        return carMapper.createCar(car) != 0;
    }

    /** 查找车辆 (ID) */
    public Car findCarByID(int id) {
        return carMapper.findCarByID(id);
    }

    /** 查找车辆 (用户 ID) */
    public Car findCarByUserID(int user_id) {
        return carMapper.findCarByUserID(user_id);
    }

    /** 查找车辆 (车牌号) */
    public Car findCarByInfo(String plate) {
        return carMapper.findCarByInfo(plate);
    }
}
