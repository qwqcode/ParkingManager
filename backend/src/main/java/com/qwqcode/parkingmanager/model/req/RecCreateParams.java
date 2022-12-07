package com.qwqcode.parkingmanager.model.req;

public class RecCreateParams {
    private String car_plate;
    private String car_plate_type;
    private int park_id;

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getCar_plate_type() {
        return car_plate_type;
    }

    public void setCar_plate_type(String car_plate_type) {
        this.car_plate_type = car_plate_type;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }
}
