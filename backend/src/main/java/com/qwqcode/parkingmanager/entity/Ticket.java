package com.qwqcode.parkingmanager.entity;

import java.util.Date;

public class Ticket {
    private int id;

    private String ticket_key;
    private int park_id;
    private int is_available;

    private Date created_at;

    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public String getTicket_key() {
        return ticket_key;
    }

    public void setTicket_key(String ticket_key) {
        this.ticket_key = ticket_key;
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
