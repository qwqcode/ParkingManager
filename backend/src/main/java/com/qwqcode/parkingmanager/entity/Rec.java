package com.qwqcode.parkingmanager.entity;

import com.qwqcode.parkingmanager.common.Utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Rec {
    private int id;
    private int car_id;
    private int park_id;
    private Date in_at;
    private Date out_at;

    private Date created_at;

    private Date updated_at;

    private int rec_pay_id;

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

    public int getRec_pay_id() {
        return rec_pay_id;
    }

    public void setRec_pay_id(int rec_pay_id) {
        this.rec_pay_id = rec_pay_id;
    }

    // 获取停车时长（分钟）
    public long getParking_time() {
        Date inDate = getIn_at();
        Date outDate = getOut_at();

        if (inDate == null) return 0;
        if (outDate == null) outDate = Utils.getNowDate();

        long diff = Math.abs(inDate.getTime() - outDate.getTime()); // 计算两个毫秒值之差
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff); // 计算时间长度（单位：分钟）

        return minutes;
    }

    // 获取停车费用
    public BigDecimal getParking_price() {
        return getParking_price(0);
    }

    public BigDecimal getParking_price(int de_hours) {
        final int PRICE_PER_HOUR = 2; // 每小时收费

        long minutes = getParking_time();
        long hours = (long)Math.ceil((double)minutes / 60.0); // 计算小时，向上取整（不满 1 小时按 1 小时来算）

        if (de_hours > 0) {
            hours -= de_hours;
            if (hours < 0) hours = 0;
        }

        if (minutes <= 30) {
            // 停车 30 分钟内免费
            return BigDecimal.valueOf(0.00);
        } else {
            // 按小时计算停车费用
            return BigDecimal.valueOf(hours * PRICE_PER_HOUR);
        }
    }

    /**
     * 当前状态（文本）
     */
    public String getStatus_text() {
        return (out_at == null ? "停泊中" : "已出场") + " " + (rec_pay_id == 0 ? "(未缴费)" : "(已缴费)");
    }
}
