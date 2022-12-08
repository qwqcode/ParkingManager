package com.qwqcode.parkingmanager.model.res;

import com.qwqcode.parkingmanager.entity.Car;

import java.util.List;

public class AdminCarsResp {
    private List<Car> cars;
    private int total;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
