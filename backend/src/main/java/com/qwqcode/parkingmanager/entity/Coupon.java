package com.qwqcode.parkingmanager.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {
    private int id;
    private BigDecimal de_price;
    private int user_id;
    private int is_available;
    private Date created_at;

    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getDe_price() {
        return de_price;
    }

    public void setDe_price(BigDecimal de_price) {
        this.de_price = de_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIs_available() {
        return is_available;
    }

    public void setIs_available(int is_available) {
        this.is_available = is_available;
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
