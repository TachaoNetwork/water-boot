/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.tool;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 日期工具类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterDateUtil {

    /** 默认的时间格式化类型 */
    protected final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /** 时间格式化类型 */
    protected final static String DATE_PATTERN_DETAIL = "yyyy-MM-dd hh mm ss";

    /**
     *
     * 方法说明：字符类型装成指定格式的时间格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date str2Date(String date, String pattern) {
        Date _d = null;
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            _d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
        }
        return _d;
    }

    /**
     *
     * 方法说明：字符类型装成指定格式的时间格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date strToDate(String date, String pattern) {
        Date _d = null;
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            _d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
        }
        return _d;
    }

    /**
     *
     * 方法说明：字符类型装成默认的时间格式
     *
     * @param date
     * @return
     */
    public static Date str2Date(String date) {
        return str2Date(date, DEFAULT_DATE_PATTERN);
    }

    /**
     *
     * 方法说明：字符类型装成默认的时间格式
     *
     * @param date
     * @return
     */
    public static Date strToDate(String date) {
        return strToDate(date, DEFAULT_DATE_PATTERN);
    }

    /**
     *
     * 方法说明：字符类型转成timestamp的时间格式
     *
     * @param date
     * @return
     */
    public static Date str2DateDetail(String date) {
        return str2Date(date, DATE_PATTERN_DETAIL);
    }

    /**
     *
     * 时间转成字符串
     *
     * @param date
     * @return
     */
    public static String date2Str(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat newstr = new SimpleDateFormat(pattern);
        return newstr.format(date);
    }

    /**
     *
     * 时间转换成字符串-默认格式"yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        return date2Str(date, DEFAULT_DATE_PATTERN);
    }


    /**
     *
     * 方法说明：某个时间点添加分钟后的时间
     *
     * @param date
     *            需要添加的时间
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     *
     * 方法说明：某个时间点添加分钟后的时间
     *
     * @param date
     *            需要添加的时间
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     *
     * 方法说明：某个时间点添加几天后的时间
     *
     * @param date
     *            某个时间
     * @param days
     *            需要添加的时间
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 添加小时
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     *
     * 格式化时间
     * @return
     */
    public static String getNowtimeStr(){
        Calendar cal = Calendar.getInstance();
        return date2Str(cal.getTime(), "yyyyMMddHHmmssSSS");
    }

    /**
     *
     * 方法说明：给日期加上一天
     *
     * @param date
     * @return
     */
    public static Date addDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     *
     * 方法说明：某个时间点添加几月后的时间
     *
     * @param date
     *            某个时间
     * @param months
     *            需要添加月数
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     *
     * 增加年份
     *
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     *
     * 取得当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     *
     * 取当前日期字符串"yyyy-MM-dd"
     *
     * @return
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return newstr.format(getCurrentDate());
    }

    /**
     *
     * 取当前日期字符串"yyyy-MM-dd"
     *
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat newstr = new SimpleDateFormat(pattern);
        return newstr.format(getCurrentDate());
    }

    /**
     *
     * 获取当前时间往前一个月的开始日期
     *
     * @return
     */
    public static String getOneMonthStartDate() {
        SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // 得到前一个月
        return newstr.format(calendar.getTime()).toString();
    }

    /**
     *
     * 获取当前时间往前一个月的结束日期
     *
     * @return
     */
    public static String getOneMonthEndDate() {
        SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return newstr.format(new Date()).toString();
    }

    /**
     *
     * 比较两个时间大小，date1<=date2返回true，反之返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        long day1 = date1.getTime();
        long day2 = date2.getTime();
        int result = 0;
        if(day1 > day2){
            result = 1;
        }else if(day1 < day2){
            result = -1;
        }
        return result;
    }

    public static Map<String, Integer> getDateFields(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        return map;
    }

    /**
     *
     * 方法说明：将指定时间重置为当天凌晨时间
     *
     * @param date
     * @return
     */
    public static Date moveBeginOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 1);
        return c.getTime();
    }


    /**
     *
     * 方法说明：将指定时间重置为当天凌晨时间
     *
     * @param date
     * @return
     */
    public static Date moveBeginOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);
        return calendar.getTime();
    }

    /**
     *
     * 方法说明：将指定时间重置为当天凌晨时间
     *
     * @param date
     * @return
     */
    public static Date moveBeginOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0); // 得到前一个月
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);
        return calendar.getTime();
    }

    /**
     *
     * 获取当前时间一年的日期
     *
     * @return
     */
    public static Date getOneYearStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0); // 得到前一个月
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);
        return calendar.getTime();
    }

    /**
     *
     * 获取当月初时间
     *
     * @return
     */
    public static Date getCurrentMonthBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     *
     * 获取指定年份开始时间
     *
     * @return
     * @throws ParseException
     */
    public static Date getYearBegin(String yearStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(sdf.parse(yearStr.toString()));
        return calendar.getTime();
    }

    public static int getYearFromDate(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.YEAR);
    }

    /**
     * 方法说明：根据年月获取初始日期
     *
     * @param yearMonth
     * @return
     */
    public static Date getDateFromYearMonth(String yearMonth) {
        DateFormat ym = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = ym.parse(yearMonth);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 方法说明：根据年月日获取日期
     *
     * @param yearMonth
     * @return
     */
    public static Date getDateFromYearMonthDay(String yearMonth) {
        DateFormat ym = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = ym.parse(yearMonth);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     *
     * 获取这个月的年份
     *
     * @return
     */
    public static int getThisMonthOfYear() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     *
     * 获取毫秒的数据
     *
     * @return
     */
    public static String getMillisecondStr() {
        String result = date2Str(getCurrentDate(), "yyMMddHHmmssSSS");
        return result;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }


}
