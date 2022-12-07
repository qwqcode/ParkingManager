package com.qwqcode.parkingmanager.entity;

import java.util.Date;

public class Park {
    private int id;
    private String addr;
    private int capacity;
    private Date open_at;
    private Date close_at;
    private int status;

    private Date created_at;

    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getOpen_at() {
        return open_at;
    }

    public void setOpen_at(Date open_at) {
        this.open_at = open_at;
    }

    public Date getClose_at() {
        return close_at;
    }

    public void setClose_at(Date close_at) {
        this.close_at = close_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
