/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-11-26
 */
package com.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理的工具类
 * 
 * @author Flouny.Caesar
 * 
 */
public class DateUtil extends DateUtils {

    private static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    /**
     * 日期+时间的格式
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String yyyyMMddHHmmss_FORMAT = "yyyyMMddHHmmss";

    /**
     * 日期的格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期的格式 年 月
     */
    public static final String MONTH_FORMAT = "yyyy年MM月";

    /**
     * 小时:分秒的格式
     */
    public static final String HHMMSS_FORMAT = "HH:mm:ss";

    /**
     * 小时:分的格式
     */
    public static final String HHMM_FORMAT = "HH:mm";

    /**
     * 将日期字符串解析成"yyyy-MM-dd"格式的Date对象
     * 
     * @param dateTime
     * @return
     */
    public static Date parseDate(String dateTime) {

        return parse(dateTime, DATE_FORMAT);
    }
    
    /**
     * 将日期字符串解析成"yyyyMMddHHmmss"格式的Date对象
     * 
     * @param dateTime
     * @return
     */
    public static Date parseDate2(String dateTime) {

        return parse(dateTime, yyyyMMddHHmmss_FORMAT);
    }

    /**
     * 将日期字符串解析成"yyyy-MM-dd HH:mm:ss"格式的Date对象
     * 
     * @param dateTime
     * @return
     */
    public static Date parseDateTime(String dateTime) {

        return parse(dateTime, DATE_TIME_FORMAT);
    }

    /**
     * 将日期字符串解析成指定格式的Date对象
     * 
     * @param dateTime
     * @param format
     * @return
     */
    public static Date parse(String dateTime, String format) {
        if (StringUtil.isBlank(dateTime))
            return null;

        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateTime);
        } catch (ParseException e) {

            throw new RuntimeException("format date error!", e);
        }
    }

    /**
     * 将日期类解析成"yyyy-MM-dd HH:mm:ss"格式的日期字符串
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {

        return formatDateTime(date, DATE_TIME_FORMAT);
    }

    /**
     * 将日期类解析成"yyyy-MM-dd"格式的日期字符串
     * 
     * @param date
     * @return
     */
    public static String formatYYYYMMDD(Date date) {

        return formatDateTime(date, DATE_FORMAT);
    }

    /**
     * 将日期类解析成"yyyy-MM"格式的日期字符串
     * 
     * @param date
     * @return
     */
    public static String formatYYYYMM(Date date) {
        return formatDateTime(date, MONTH_FORMAT);
    }

    /**
     * 将日期类解析成指定格式的日期字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String formatDateTime(Date date, String format) {
        if (date == null)
            return null;

        DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }

    /**
     * 返回当月的星期几
     * 
     * @param date
     * @return
     */
    public static String formatWeekInMonth(Date date) {
        if (date == null)
            return null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return dayNames[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 返回时间差(秒)
     * 
     * @param old
     * @return
     */
    public static int getDiffeSeconds(Date old) {
        if (old == null)
            return 0;

        return getDiffeMinute(null, old);
    }

    /**
     * 返回时间差(秒)
     * 
     * @param now
     * @param old
     * @return
     */
    public static int getDiffeSeconds(Date now, Date old) {
        if (old == null)
            return 0;
        if (now == null)
            now = new Date();

        return (int) ((now.getTime() - old.getTime()) / 1000);
    }

    /**
     * 返回时间差(分钟)
     * 
     * @param old
     * @return
     */
    public static int getDiffeMinute(Date old) {
        if (old == null)
            return 0;

        return getDiffeMinute(null, old);
    }

    /**
     * 返回时间差(分钟)
     * 
     * @param now
     * @param old
     * @return
     */
    public static int getDiffeMinute(Date now, Date old) {
        if (old == null)
            return 0;
        if (now == null)
            now = new Date();

        return (int) ((now.getTime() - old.getTime()) / (1000 * 60));
    }

    /**
     * 返回时间差(小时)
     * 
     * @param old
     * @return
     */
    public static int getDiffeHour(Date old) {
        if (old == null)
            return 0;

        return getDiffeHour(null, old);
    }

    /**
     * 返回时间差(小时)
     * 
     * @param now
     * @param old
     * @return
     */
    public static int getDiffeHour(Date now, Date old) {
        if (old == null)
            return 0;
        if (now == null)
            now = new Date();

        return (int) ((now.getTime() - old.getTime()) / (1000 * 60 * 60));
    }

    /**
     * 返回时间差(天)
     * 
     * @param old
     * @return
     */
    public static int getDiffeDay(Date old) {
        if (old == null)
            return 0;

        return getDiffeDay(null, old);
    }

    /**
     * 返回时间差(天>小时>分)
     * 
     * @param now
     * @param old
     * @return
     */
    public static String getDiffeDayHourMinute(Date old) {
        int m = (int) (new Date().getTime() - old.getTime()) / (1000 * 60);
        int day = m / (24 * 60);
        m %= (24 * 60);
        int h = m / (60);
        m %= 60;
        if (day != 0)
            return day + "天";
        if (h != 0)
            return h + "小时";
        return m + "分钟";
    }

    /**
     * 返回离当前时间时间差()
     * 
     * @param now
     * @param old
     * @return
     */
    public static int getDiffeDay(Date now, Date old) {
        if (old == null)
            return 0;
        if (now == null)
            now = new Date();

        return (int) ((now.getTime() - old.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 
     * @Description 支付用
     * @param date
     * @return
     * @author zhaoyonglian
     * @date 2015年8月10日 下午4:47:49
     */
    public static String formatTime(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /**
     * 
     * @Description 支付用
     * @param date
     * @return
     * @author zhaoyonglian
     * @date 2015年8月10日 下午4:47:49
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 返回当月的第一天
     * 
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 按照"yyyyMMddHHmmss"格式化时间
     * 
     * @param date
     * @return
     */
    public static String formatYMDHMS(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /**
     * 返回离当前时间时间差()
     * 
     * @param now
     * @param old
     * @return
     */
    public static Boolean getDiffeDayNow(Date old) {
        if (old == null)
            return false;
        Long result = (Long) (old.getTime() - System.currentTimeMillis());

        return result >= 0;
    }

    /**
     * 返回离当前时间时间差()
     * 
     * @param now
     * @param old
     * @return
     */
    public static Long getDiffeDayNowInt(Date old) {

        Long result = (Long) (old.getTime()/ (1000 * 60 * 60 * 24) - System.currentTimeMillis()/ (1000 * 60 * 60 * 24)) ;
        if (old.getTime() - System.currentTimeMillis() >= 0) {
            if (result == 0) {
                return 1l;
            }
        }
        return result + 1l;

    }
    
    
    public static Date addDay(String s, int n) {   
        try {   
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);   
  
            Calendar cd = Calendar.getInstance();   
            cd.setTime(sdf.parse(s));   
            cd.add(Calendar.DATE, n);//增加一天   
            //cd.add(Calendar.MONTH, n);//增加一个月   
  
            return cd.getTime();//sdf.parse(sdf.format(cd.getTime()));   
  
        } catch (Exception e) {   
            return null;   
        }   
  
    }

    /**
     * 计算两个时间点的间隔天数（四舍五入）
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        int between_days=Math.round((time2-time1)/(1000*3600*24));

        return between_days;
    }

    /**
     * 计算两个时间点的间隔小时数（四舍五入并保留以为小数）
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static float hoursBetween(Date startDate,Date endDate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startDate=sdf.parse(sdf.format(startDate));
        endDate=sdf.parse(sdf.format(endDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        float between_hours=(Math.round(((time2-time1)/(1000*3600))*10))/10;

        return between_hours;
    }

}