package com.qwqcode.parkingmanager.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class Car {
    private int id;
    private String plate;
    private String plate_type;
    private int user_id;

    private Date created_at;

    private Date updated_at;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Rec> recs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlate_type() {
        return plate_type;
    }

    public void setPlate_type(String plate_type) {
        this.plate_type = plate_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public List<Rec> getRecs() {
        return recs;
    }

    public void setRecs(List<Rec> recs) {
        this.recs = recs;
    }
}
