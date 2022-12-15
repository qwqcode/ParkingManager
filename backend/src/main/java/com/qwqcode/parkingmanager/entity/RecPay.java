package com.qwqcode.parkingmanager.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RecPay {
    private int id;
    private int car_id;
    private int rec_id;
    private int park_id;

    private int user_id;
    private BigDecimal price;
    private int use_ticket_id;
    private int use_coupon_id;
    private int is_use_vip_card;

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

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUse_ticket_id() {
        return use_ticket_id;
    }

    public void setUse_ticket_id(int use_ticket_id) {
        this.use_ticket_id = use_ticket_id;
    }

    public int getUse_coupon_id() {
        return use_coupon_id;
    }

    public void setUse_coupon_id(int use_coupon_id) {
        this.use_coupon_id = use_coupon_id;
    }

    public int getIs_use_vip_card() {
        return is_use_vip_card;
    }

    public void setIs_use_vip_card(int is_use_vip_card) {
        this.is_use_vip_card = is_use_vip_card;
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
