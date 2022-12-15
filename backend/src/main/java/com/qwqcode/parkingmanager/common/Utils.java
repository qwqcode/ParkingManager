package com.qwqcode.parkingmanager.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    public static Date getNowDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();

        return date;
    }

    public static String getDateFormatted(Date date) {
        if (date == null) return "";
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormat.format(date);
    }

    public static String getTime2HourMin(long minutes) {
        if (minutes < 60) return minutes + " 分钟";
        return (long)Math.floor(minutes/60.0) + " 小时 " + (minutes%60) + " 分钟";
    }
}
