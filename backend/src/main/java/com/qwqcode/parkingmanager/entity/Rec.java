package com.qwqcode.parkingmanager.entity;

import java.util.Date;

public class Rec {
    private int id;
    private int car_id;
    private int park_id;
    private Date in_at;
    private Date out_at;

    private Date created_at;

    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public Date getIn_at() {
        return in_at;
    }

    public void setIn_at(Date in_at) {
        this.in_at = in_at;
    }

    public Date getOut_at() {
        return out_at;
    }

    public void setOut_at(Date out_at) {
        this.out_at = out_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
