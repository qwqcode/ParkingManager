package com.qwqcode.parkingmanager.entity;

import java.util.Date;

public class TicketPreset {
    private int id;
    private String cond;
    private String cond_val;
    private String act;
    private String act_val;
    private int is_available;

    private Date created_at;

    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getCond_val() {
        return cond_val;
    }

    public void setCond_val(String cond_val) {
        this.cond_val = cond_val;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getAct_val() {
        return act_val;
    }

    public void setAct_val(String act_val) {
        this.act_val = act_val;
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
