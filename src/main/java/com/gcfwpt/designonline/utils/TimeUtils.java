package com.gcfwpt.designonline.utils;/**
 * Created by yjx on 15/5/2.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: YJX
 * Date: 2015-05-02
 * Time: 15:39
 */
public final class TimeUtils {

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     * @param timeStr   时间戳
     * @return
     */
    public static String getTimeDistance(String timeStr) {

        int t = Integer.parseInt(timeStr);
        int time = (int)(System.currentTimeMillis()/1000L);
        int b = Math.abs(time);
        int timeDistance = t - b;
        String timeString;
        if(0 <= timeDistance && timeDistance <= 1800){
            String minute = String.valueOf(timeDistance/60);
            String second = String.valueOf(timeDistance%60);
            if(Integer.parseInt(minute) < 10){
                minute = "0" + minute;
            }
            if(Integer.parseInt(second) < 10){
                second = "0" + second;
            }
            timeString = minute  + "’" + second+"”";
            return timeString;
        }else{
            return "0";
        }
    }

    // time 时间戳 eeee代表星期几
    public static String parseTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+ss:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
        try {
            Date date = new Date(Long.parseLong(time));
            return sdf1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    // time 时间戳 eeee代表星期几
    public static String parseTime2(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = new Date(Long.parseLong(time) * 1000);
            return sdf1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    // time 时间戳 eeee代表星期几
    public static String parseTime3(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");
        try {
            Date date = new Date(Long.parseLong(time) * 1000);
            return sdf1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    // time 时间戳 eeee代表星期几
    public static String parseTimeDay(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        try {
            Date date = new Date(Long.parseLong(time));
            return sdf1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    // time 时间戳 eeee代表星期几
    public static String parseTimeWeek(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
        try {
            Date date = new Date(Long.parseLong(time));
            return sdf1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    // time 时间戳 eeee代表星期几
    public static String parseTimeDaytime(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = new Date(Long.parseLong(time));
            return sdf1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

}
