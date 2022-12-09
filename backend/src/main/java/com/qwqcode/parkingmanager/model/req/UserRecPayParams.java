package com.qwqcode.parkingmanager.model.req;

public class UserRecPayParams {
    private int is_challenge;

    private int rec_id;

    private String ticket_key;

    private int coupon_id;

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public String getTicket_key() {
        return ticket_key;
    }

    public void setTicket_key(String ticket_key) {
        this.ticket_key = ticket_key;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getIs_challenge() {
        return is_challenge;
    }

    public void setIs_challenge(int is_challenge) {
        this.is_challenge = is_challenge;
    }
}
