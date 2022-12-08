package com.qwqcode.parkingmanager.model.res;

import com.qwqcode.parkingmanager.entity.Car;

import java.util.List;

public class UserCarsResp {
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
