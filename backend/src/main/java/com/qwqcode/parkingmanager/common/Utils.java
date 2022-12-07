package com.qwqcode.parkingmanager.common;

import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static Date getNowDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();

        return date;
    }
}
