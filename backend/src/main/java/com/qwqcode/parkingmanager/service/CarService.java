package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Car;
import com.qwqcode.parkingmanager.mapper.CarMapper;
import com.qwqcode.parkingmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /** 获取车辆列表（分页）*/
    public List<Car> findAllCars(int offset, int limit) {
        return carMapper.findAllCars(offset, limit);
    }

    /** 获取用户车辆列表 */
    public List<Car> findUserCars(int user_id) {
        return carMapper.findUserCars(user_id);
    }

    /** 获取数据表中的所有车辆总数 */
    public int countAllCars() {
        return carMapper.countAllCars();
    }

    /** 修改车辆 user_id */
    public boolean editCarUserID(int car_id, int user_id) {
        return carMapper.editCarUserID(car_id, user_id) != 0;
    }
}
