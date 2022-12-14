package com.qwqcode.parkingmanager.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qwqcode.parkingmanager.common.Utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Rec {
    private int id;
    private int car_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String car_plate;

    private int park_id;
    private Date in_at;
    private Date out_at;

    private Date created_at;

    private Date updated_at;

    private int rec_pay_id;

    private Date rec_pay_at;

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

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
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

    public int getRec_pay_id() {
        return rec_pay_id;
    }

    public void setRec_pay_id(int rec_pay_id) {
        this.rec_pay_id = rec_pay_id;
    }

    public Date getRec_pay_at() {
        return rec_pay_at;
    }

    public void setRec_pay_at(Date rec_pay_at) {
        this.rec_pay_at = rec_pay_at;
    }

    // ??????????????????????????????
    public long getParking_time() {
        Date inDate = getIn_at();
        Date outDate = getOut_at();

        if (inDate == null) return 0;
        if (outDate == null) outDate = Utils.getNowDate();

        long diff = Math.abs(inDate.getTime() - outDate.getTime()); // ???????????????????????????
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff); // ???????????????????????????????????????

        return minutes;
    }

    public String getParking_time_text() {
        return Utils.getTime2HourMin(this.getParking_time());
    }

    // ??????????????????
    public BigDecimal getParking_price() {
        return getParking_price(0);
    }

    public BigDecimal getParking_price(int de_hours) {
        final int PRICE_PER_HOUR = 2; // ???????????????

        long minutes = getParking_time();
        long hours = (long)Math.ceil((double)minutes / 60.0); // ???????????????????????????????????? 1 ????????? 1 ???????????????

        if (de_hours > 0) {
            hours -= de_hours;
            if (hours < 0) hours = 0;
        }

        if (minutes <= 30) {
            // ?????? 30 ???????????????
            return BigDecimal.valueOf(0.00);
        } else {
            // ???????????????????????????
            return BigDecimal.valueOf(hours * PRICE_PER_HOUR);
        }
    }

    /**
     * ????????????????????????
     */
    public String getStatus_text() {
        return (out_at == null ? "?????????" : "?????????") + " " + (rec_pay_id == 0 ? "(?????????)" : "(?????????)");
    }

    public String getIn_at_formatted() {
        return Utils.getDateFormatted(this.in_at);
    }

    public String getOut_at_formatted() {
        return Utils.getDateFormatted(this.out_at);
    }
}
