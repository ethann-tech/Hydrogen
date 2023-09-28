/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ethan.hydrogen.utils;


import androidx.annotation.Keep;

import com.ethan.hydrogen.date.SpecialCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author ethan
 */
@Keep
public class DateUtil {
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR = "yyyy-MM-dd HH";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YEAR_MONTH = "yyyy-MM";
    public static final String DATE_FORMAT_YEAR = "yyyy";
    public static final String DATE_FORMAT_MONTH = "MM";
    public static final String DATE_FORMAT_DAY = "dd";
    public static final String DATE_FORMAT_MONTH_DAY = "MM-dd";
    public static final String DATE_FORMAT_HOUR_MIN_SECOND = "HH:mm:ss";
    public static final String DATE_FORMAT_HOUR_MIN = "HH:mm";
    public static final String DATE_FORMAT_DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND_SSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private DateUtil() {}

    private static class Inner {
        private static final DateUtil INSTANCE = new DateUtil();
    }

    /**
     * 实例对象
     */
    public static DateUtil getInstance() {
        return Inner.INSTANCE;
    }


    /**
     * yyyy-MM-dd HH:mm:ss格式
     */
    public final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = ThreadLocal.withInitial(
            () -> new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN_SECOND, Locale.CHINA));

    public final ThreadLocal<SimpleDateFormat> defaultDateTimeFormatNoSecond = ThreadLocal.withInitial(
            () -> new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN, Locale.CHINA));

    /**
     * yyyy-MM-dd格式
     */
    public final ThreadLocal<SimpleDateFormat> defaultDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY, Locale.CHINA));

    /**
     * HH:mm:ss格式
     */
    public final ThreadLocal<SimpleDateFormat> defaultTimeFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_FORMAT_HOUR_MIN_SECOND, Locale.CHINA));


    /**
     * 将long时间转成yyyy-MM-dd HH:mm:ss字符串<br>
     *
     * @param timeInMillis 时间long值
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getDateTimeFromMillis(long timeInMillis) {
        return getDateTimeFormat(new Date(timeInMillis));
    }

    /**
     * 将long时间转成yyyy-MM-dd字符串<br>
     *
     * @param timeInMillis 毫秒时间
     * @return yyyy-MM-dd
     */
    public String getDateFromMillis(long timeInMillis) {
        return getDateFormat(new Date(timeInMillis));
    }

    /**
     * 将date转成yyyy-MM-dd HH:mm:ss字符串
     * <br>
     *
     * @param date Date对象
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getDateTimeFormat(Date date) {
        return dateSimpleFormat(date, defaultDateTimeFormat.get());
    }

    /**
     * 将年月日的int转成yyyy-MM-dd的字符串
     *
     * @param year  年
     * @param month 月 1-12
     * @param day   日
     *              注：月表示Calendar的月，比实际小1
     *              对输入项未做判断
     */
    public String getDateFormat(int year, int month, int day) {
        return getDateFormat(getDate(year, month, day));
    }

    /**
     * 将date转成yyyy-MM-dd字符串<br>
     *
     * @param date Date对象
     * @return yyyy-MM-dd
     */
    public String getDateFormat(Date date) {
        return dateSimpleFormat(date, defaultDateFormat.get());
    }

    /**
     * 获得HH:mm:ss的时间
     *
     * @param date
     * @return
     */
    public String getTimeFormat(Date date) {
        return dateSimpleFormat(date, defaultTimeFormat.get());
    }

    /**
     * 格式化日期显示格式
     *
     * @param sdate  原始日期格式 "yyyy-MM-dd"
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
    public String dateFormat(String sdate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        java.sql.Date date = java.sql.Date.valueOf(sdate);
        return dateSimpleFormat(date, formatter);
    }

    /**
     * 格式化日期显示格式
     *
     * @param date   Date对象
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
    public String dateFormat(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        return dateSimpleFormat(date, formatter);
    }

    /**
     * 将date转成字符串
     *
     * @param date   Date
     * @param format SimpleDateFormat
     *               <br>
     *               注： SimpleDateFormat为空时，采用默认的yyyy-MM-dd HH:mm:ss格式
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String dateSimpleFormat(Date date, SimpleDateFormat format) {
        if(format == null)
            format = defaultDateTimeFormat.get();
        assert format != null;
        return (date == null ? "" : format.format(date));
    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss" 格式的字符串转成Date
     *
     * @param strDate 时间字符串
     * @return Date
     */
    public Date getDateByDateTimeFormat(String strDate) {
        return getDateByFormat(strDate, defaultDateTimeFormat.get());
    }

    /**
     * 将"yyyy-MM-dd" 格式的字符串转成Date
     *
     * @param strDate
     * @return Date
     */
    public Date getDateByDateFormat(String strDate) {
        return getDateByFormat(strDate, defaultDateFormat.get());
    }

    /**
     * 将指定格式的时间字符串转成Date对象
     *
     * @param strDate 时间字符串
     * @param format  格式化字符串
     * @return Date
     */
    public Date getDateByFormat(String strDate, String format) {
        return getDateByFormat(strDate, new SimpleDateFormat(format, Locale.CHINA));
    }

    /**
     * 将String字符串按照一定格式转成Date<br>
     * 注： SimpleDateFormat为空时，采用默认的yyyy-MM-dd HH:mm:ss格式
     *
     * @param strDate 时间字符串
     * @param format  SimpleDateFormat对象
     * @throws ParseException 日期格式转换出错
     */
    private Date getDateByFormat(String strDate, SimpleDateFormat format) {
        if(format == null)
            format = defaultDateTimeFormat.get();
        try {
            assert format != null;
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将年月日的int转成date
     *
     * @param year  年
     * @param month 月 1-12
     * @param day   日
     *              注：月表示Calendar的月，比实际小1
     */
    public Date getDate(int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(year, month - 1, day);
        return mCalendar.getTime();
    }

    /**
     * 求两个日期相差天数
     *
     * @param strat 起始日期，格式yyyy-MM-dd
     * @param end   终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public long getIntervalDays(String strat, String end) {
        return ((java.sql.Date.valueOf(end)).getTime() - (java.sql.Date.valueOf(strat)).getTime()) / (3600 * 24 * 1000);
    }

    /**
     * 获得当前年份
     *
     * @return year(int)
     */
    public int getCurrentYear() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * 获得当前月份
     *
     * @return month(int) 1-12
     */
    public int getCurrentMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当月几号
     *
     * @return day(int)
     */
    public int getDayOfMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得今天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public String getToday() {
        Calendar mCalendar = Calendar.getInstance();
        return getDateFormat(mCalendar.getTime());
    }

    /**
     * 获得昨天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public String getYesterday() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, -1);
        return getDateFormat(mCalendar.getTime());
    }

    /**
     * 获得前天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public String getBeforeYesterday() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, -2);
        return getDateFormat(mCalendar.getTime());
    }

    /**
     * 获得几天之前或者几天之后的日期
     *
     * @param diff 差值：正的往后推，负的往前推
     * @return
     */
    public String getOtherDay(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, diff);
        return getDateFormat(mCalendar.getTime());
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param sDate  给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public String getCalcDateFormat(String sDate, int amount) {
        Date date = getCalcDate(getDateByDateFormat(sDate), amount);
        return getDateFormat(date);
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date   给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public Date getCalcDate(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * 获得一个计算十分秒之后的日期对象
     *
     * @param date
     * @param hOffset 时偏移量，可为负
     * @param mOffset 分偏移量，可为负
     * @param sOffset 秒偏移量，可为负
     * @return
     */
    public Date getCalcTime(Date date, int hOffset, int mOffset, int sOffset) {
        Calendar cal = Calendar.getInstance();
        if(date != null)
            cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hOffset);
        cal.add(Calendar.MINUTE, mOffset);
        cal.add(Calendar.SECOND, sOffset);
        return cal.getTime();
    }

    /**
     * 根据指定的年月日小时分秒，返回一个java.Util.Date对象。
     *
     * @param year      年
     * @param month     月 0-11
     * @param date      日
     * @param hourOfDay 小时 0-23
     * @param minute    分 0-59
     * @param second    秒 0-59
     * @return 一个Date对象
     */
    public Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }


    /**
     * 获得年月日数据
     *
     * @param sDate yyyy-MM-dd格式
     * @return arr[0]:年， arr[1]:月 0-11 , arr[2]日
     */
    public int[] getYearMonthAndDayFrom(String sDate) {
        return getYearMonthAndDayFromDate(getDateByDateFormat(sDate));
    }

    /**
     * 获得年月日数据
     *
     * @return arr[0]:年， arr[1]:月 0-11 , arr[2]日
     */
    public int[] getYearMonthAndDayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] arr = new int[3];
        arr[0] = calendar.get(Calendar.YEAR);
        arr[1] = calendar.get(Calendar.MONTH);
        arr[2] = calendar.get(Calendar.DAY_OF_MONTH);
        return arr;
    }


    /**
     * 获得当天0点时间， 起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     *
     * @return 当天0点时间 单位毫秒
     */
    public long getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获得当天24点时间， 起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     *
     * @return 当天24点时的毫秒数
     */
    public long getTimesNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获得本周一0点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     *
     * @return 获得本周一0点时刻的毫秒数
     */
    public long getTimesWeekMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTimeInMillis();
    }

    /**
     * 获得本周日24点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     *
     * @return 获得本周日24点时刻的毫秒数
     */
    public long getTimesWeekNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000);
    }

    /**
     * 获得本月第一天0点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     *
     * @return 获得本月第一天0点时刻的毫秒数
     */
    public long getTimesMonthMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }

    /**
     * 获得本月最后一天24点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     *
     * @return 获得本月最后一天24点时间
     */
    public long getTimesMonthNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTimeInMillis();
    }

    /**
     * 获得指定日期对应的星期日  改日期返回值0~6 分别表示 星期日-星期六 如果是周日的话， dayofWeek 为0
     *
     * @param year  年 例 2020
     * @param month 月 例 1
     * @param day   日 例 14
     * @return 2  表示星期二
     */
    public static int getDayOfWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取指定指定月份的天数
     *
     * @param year  2020
     * @param month 1
     * @return 31  单位（天）
     */
    private static int getNumberOfDayByMonth(int year, int month) {
        SpecialCalendar sc = new SpecialCalendar();
        return sc.getDaysOfMonth(sc.isLeapYear(year), month);
    }


    /**
     * 获取当天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY, Locale.ENGLISH);
        return format.format(date);
    }

    /**
     * 格式化日期
     *
     * @param date   被格式化的日期
     * @param format 被格式化日期的格式
     * @return 格式化后的日期
     */
    public String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.format(date);
    }

    /**
     * 格式化日期
     *
     * @param date   被格式化的日期
     * @param format 被格式化日期的格式
     * @return 格式化后的日期
     */
    public String formatDate(long date, String format) {
        return formatDate(new Date(date), format);
    }

    /**
     * 将日期解析为毫秒
     *
     * @param date   被解析日期
     * @param format 被解析日期的格式
     * @return 毫秒 东八区时间
     */
    public long parseDateToMilliseconds(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
            LocalDateTime ldt = LocalDateTime.parse(date, formatter);
            return ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 解析日期
     *
     * @param date   被解析日期
     * @param format 被解析日期的格式
     * @return 解析后的日期
     */
    public Date parseDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期转行成秒
     *
     * @param date 被转换日期
     * @return 秒
     */
    public static long convertToSeconds(Date date) {
        return date.getTime() / 1000L;
    }

    /**
     * 将日期转换成分钟
     *
     * @param date 被转换日期
     * @return 分钟
     */
    public static long convertToMinutes(Date date) {
        return convertToSeconds(date) / 60L;
    }

    /**
     * 将日期转换成小时
     *
     * @param date 日期
     * @return 转换后的小时
     */
    public static long convertToHours(Date date) {
        return convertToMinutes(date) / 60L;
    }

    /**
     * 获取年份
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getYear(date, Locale.CHINA);
    }

    /**
     * 获取年份
     *
     * @param date   日期
     * @param locale 位置
     * @return 年份
     */
    public static int getYear(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }


    /**
     * <查询日期范围内的所有日期>
     *
     * @param dayBegin 开始日期
     * @param dayEnd   结束日期
     * @return list
     */
    public static List<Date> findDates(Date dayBegin, Date dayEnd) {
        List<Date> listDate = new ArrayList<>();
        // lDate.add(dBegin);
        Calendar calendarBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calendarBegin.setTime(dayBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dayEnd);
        // 测试此日期是否在指定日期之后
        while (dayEnd.after(calendarBegin.getTime())) {
            listDate.add(calendarBegin.getTime());
            calendarBegin.add(Calendar.DAY_OF_MONTH, 1);
        }
        listDate.add(calEnd.getTime());
        return listDate;
    }

    /**
     * <查询日期范围内的所有日期>
     *
     * @param dayBegin     开始日期
     * @param formatBegin  开始日期 格式
     * @param dayEnd       结束日期
     * @param formatEnd    结束日期格式
     * @param formatResult 两个日期之间的日期格式
     * @return 两个日期之间的日期集合
     */
    public List<String> findDates(String dayBegin, String formatBegin, String dayEnd, String formatEnd, String formatResult) {
        Date dateBegin = parseDate(dayBegin, formatBegin);
        Date dateEnd = parseDate(dayEnd, formatEnd);
        return findDates(dateBegin, dateEnd).stream().map(date -> formatDate(date, formatResult)).collect(Collectors.toList());
    }

    public String prettyDuration(long durationMillis) {
        long seconds = durationMillis / 1000;
        boolean backward = seconds < 0;
        String suffix = backward ? "后" : "前";
        seconds = Math.abs(seconds);
        if(seconds < 5) {
            return backward ? "即将" : "刚刚";
        }
        if(seconds < 60) {
            return seconds + "秒" + suffix;
        }
        seconds /= 60;
        if(seconds < 29) {
            return seconds + "分钟" + suffix;
        }
        if(seconds < 31) {
            return "半小时" + suffix;
        }
        if(seconds < 60) {
            return seconds + "分钟" + suffix;
        }
        long m = seconds % 60;
        seconds /= 60;
        if(seconds < 24) {
            return (28 < m && m < 32 && seconds < 10 ? seconds + "个半小时" : seconds + "小时" + ((m != 0) ? m + "分钟" : "")) + suffix;
        }
        seconds /= 24;
        if(seconds < 30) {
            if(seconds % 7 == 0) {
                return seconds / 7 + "周" + suffix;
            }
            return seconds + "天" + suffix;
        }
        if(seconds < 365) {
            return seconds / 30 + "月" + suffix;
        }
        return seconds / 365 + "年" + suffix;
    }

    /**
     * 判断是否过期
     *
     * @param time      待判断时间
     * @param expiredIn 有效期
     * @return 是否过期
     */
    public static boolean isTimeOut(Long time, Long expiredIn) {
        return (System.currentTimeMillis() - time) > expiredIn;
    }

    /**
     * 判断是否过期
     *
     * @param time      待判断时间
     * @param expiredIn 有效期
     * @return 是否过期
     */
    public static boolean isTimeOut(Date time, Long expiredIn) {
        return (System.currentTimeMillis() - time.getTime()) > expiredIn;
    }


}
