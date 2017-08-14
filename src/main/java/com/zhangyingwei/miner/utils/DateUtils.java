package com.zhangyingwei.miner.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangyw on 2017/8/14.
 */
public class DateUtils {

    public static String getCurrentDateTime(){
        return getDateTimeAfter(0);
    }

    public static String getDateTimeAfter(int step) {
        return getDateAfter(0,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDate(){
        return getDateAfter(0);
    }

    public static String getDateAfter(int step) {
        return getDateAfter(step, "yyyy-MM-dd");
    }

    public static String getDateAfter(int step, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND,step);
        return simpleDateFormat.format(calendar.getTime());
    }

}
