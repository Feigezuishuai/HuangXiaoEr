package com.zz.huangxiaoer.utils;

import java.text.SimpleDateFormat;

/**
 * Created by ${韩永光} on on 2017/11/14 0014 17:46..
 */

public class TimeUtils {
    public static String formatTime(int time) {
        if (time <= 0) {
            return "00:00";
        }
        String pattern="yyyy/MM/dd HH:mm:ss";
        if (time < 1000 * 60 * 60) {
            pattern = "mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(time);
        return format;
    }
}
