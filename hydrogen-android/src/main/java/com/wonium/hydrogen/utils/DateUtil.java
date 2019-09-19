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

package com.wonium.hydrogen.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

/**
 * @ClassName: DateUtil.java
 * @Description: 日期工具类
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/12 21:03
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/12 21:03
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
@Keep
public class DateUtil {
    /**
     * 实例对象
     */
    private static DateUtil mInstance;
    public static DateUtil getInstance(){
        if (mInstance==null){
            synchronized (DateUtil.class){
                if (mInstance==null){
                    mInstance =new DateUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * yyyy-MM-dd HH:mm:ss字符串
     */
    public  final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String DEFAULT_DATE_TIME_FORMAT_NO_SECOND="yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd字符串
     */
    public  final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";

    /**
     * HH:mm:ss字符串
     */
    public  final String DEFAULT_FORMAT_TIME = "HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss格式
     */
    public  final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT, Locale.CHINA);
        }
    };

    public final ThreadLocal<SimpleDateFormat> defaultDateTimeFormatNoSecond =new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT_NO_SECOND,Locale.CHINA);
        }
    };

    /**
     * yyyy-MM-dd格式
     */
    public  final ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_FORMAT_DATE, Locale.CHINA);
        }
    };

    /**
     * HH:mm:ss格式
     */
    public  final ThreadLocal<SimpleDateFormat> defaultTimeFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_FORMAT_TIME, Locale.CHINA);
        }

    };


    /**
     * 将long时间转成yyyy-MM-dd HH:mm:ss字符串<br>
     *
     * @param timeInMillis 时间long值
     * @return yyyy-MM-dd HH:mm:ss
     */
    public  String getDateTimeFromMillis(long timeInMillis) {
        return getDateTimeFormat(new Date(timeInMillis));
    }

    /**
     * 将long时间转成yyyy-MM-dd字符串<br>
     *
     * @param timeInMillis 毫秒时间
     * @return yyyy-MM-dd
     */
    public  String getDateFromMillis(long timeInMillis) {
        return getDateFormat(new Date(timeInMillis));
    }

    /**
     * 将date转成yyyy-MM-dd HH:mm:ss字符串
     * <br>
     *
     * @param date Date对象
     * @return yyyy-MM-dd HH:mm:ss
     */
    public  String getDateTimeFormat(Date date) {
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
    public  String getDateFormat(int year, int month, int day) {
        return getDateFormat(getDate(year, month, day));
    }

    /**
     * 将date转成yyyy-MM-dd字符串<br>
     *
     * @param date Date对象
     * @return yyyy-MM-dd
     */
    public  String getDateFormat(Date date) {
        return dateSimpleFormat(date, defaultDateFormat.get());
    }

    /**
     * 获得HH:mm:ss的时间
     *
     * @param date
     * @return
     */
    public  String getTimeFormat(Date date) {
        return dateSimpleFormat(date, defaultTimeFormat.get());
    }

    /**
     * 格式化日期显示格式
     *
     * @param sdate  原始日期格式 "yyyy-MM-dd"
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
    public  String dateFormat(String sdate, String format) {
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
    public  String dateFormat(Date date, String format) {
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
    public  String dateSimpleFormat(Date date, SimpleDateFormat format) {
        if (format == null)
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
    public  Date getDateByDateTimeFormat(String strDate) {
        return getDateByFormat(strDate, defaultDateTimeFormat.get());
    }

    /**
     * 将"yyyy-MM-dd" 格式的字符串转成Date
     *
     * @param strDate
     * @return Date
     */
    public  Date getDateByDateFormat(String strDate) {
        return getDateByFormat(strDate, defaultDateFormat.get());
    }

    /**
     * 将指定格式的时间字符串转成Date对象
     *
     * @param strDate 时间字符串
     * @param format  格式化字符串
     * @return Date
     */
    public  Date getDateByFormat(String strDate, String format) {
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
    private  Date getDateByFormat(String strDate, SimpleDateFormat format) {
        if (format == null)
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
    public  Date getDate(int year, int month, int day) {
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
    public  long getIntervalDays(String strat, String end) {
        return ((java.sql.Date.valueOf(end)).getTime() - (java.sql.Date.valueOf(strat)).getTime()) / (3600 * 24 * 1000);
    }

    /**
     * 获得当前年份
     *
     * @return year(int)
     */
    public  int getCurrentYear() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * 获得当前月份
     *
     * @return month(int) 1-12
     */
    public  int getCurrentMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当月几号
     *
     * @return day(int)
     */
    public  int getDayOfMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得今天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public  String getToday() {
        Calendar mCalendar = Calendar.getInstance();
        return getDateFormat(mCalendar.getTime());
    }

    /**
     * 获得昨天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public  String getYesterday() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, -1);
        return getDateFormat(mCalendar.getTime());
    }

    /**
     * 获得前天的日期(格式：yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public  String getBeforeYesterday() {
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
    public  String getOtherDay(int diff) {
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
    public  String getCalcDateFormat(String sDate, int amount) {
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
    public  Date getCalcDate(Date date, int amount) {
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
    public  Date getCalcTime(Date date, int hOffset, int mOffset, int sOffset) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
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
    public  Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
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
    public  int[] getYearMonthAndDayFrom(String sDate) {
        return getYearMonthAndDayFromDate(getDateByDateFormat(sDate));
    }

    /**
     * 获得年月日数据
     *
     * @return arr[0]:年， arr[1]:月 0-11 , arr[2]日
     */
    public  int[] getYearMonthAndDayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] arr = new int[3];
        arr[0] = calendar.get(Calendar.YEAR);
        arr[1] = calendar.get(Calendar.MONTH);
        arr[2] = calendar.get(Calendar.DAY_OF_MONTH);
        return arr;
    }



    /**
     * 返回时间间隔的常用表示 例如：3天前、3分钟前、刚刚等等
     *
     * @param millis 时间戳 单位是毫秒
     * @param ignoreLater 是否忽略将来时间常用表示
     * @return ignoreLater如果为true，timestamp如果是将来时间则使用修正的表示方式（“刚刚”）。
     */
    public  String getTimeIntervalIntro(long millis, boolean ignoreLater) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis );
        Calendar curCalendar = Calendar.getInstance();
        //如果需要转换的时间比当前时间晚,且忽略将来时间表示
        if (calendar.after(curCalendar) && ignoreLater) {
            return "刚刚";
        }
        // 和当前时间的年份和月份是否相同
        if (calendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == curCalendar.get(Calendar.MONTH)) {
            // 年月日是否都相同
            int day1 = calendar.get(Calendar.DAY_OF_YEAR);
            int day2 = curCalendar.get(Calendar.DAY_OF_YEAR);
            if (day1 < day2) {//以前
                long hour1 = calendar.getTimeInMillis();
                long hour2 = curCalendar.getTimeInMillis();
                long distance = (hour2 - hour1) / 1000 / 60 / 60;
                if (distance < 24) {
                    return distance + "小时前";
                } else {
                    SimpleDateFormat sdf = defaultDateFormat.get();
                    return sdf.format(calendar.getTime());
                }

                //return (day2 - day1) + "天前";
                //以后
            } else if (day1 > day2) {
                return (day1 - day2) + "天后";
            } else {
                int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
                int hour2 = curCalendar.get(Calendar.HOUR_OF_DAY);
                if (hour1 < hour2) {//以前
                    return (hour2 - hour1) + "小时前";
                } else if (hour1 > hour2) {//以后
                    return (hour1 - hour2) + "小时后";
                } else {
                    int minute1 = calendar.get(Calendar.MINUTE);
                    int minute2 = curCalendar.get(Calendar.MINUTE);
                    if (minute1 < minute2) {//以前
                        return (minute2 - minute1) + "分钟前";
                    } else if (minute1 > minute2) {//以后
                        return (minute1 - minute2) + "分钟后";
                    } else {
                        //忽略秒
                        return "刚刚";
                    }
                }
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(calendar.getTime());
        }

    }


    /**
     * 返回时间间隔的常用表示 例如：3天前、3分钟前、刚刚等等
     *
     * @param datetime    2016-08-15 18:30:06
     * @param ignoreLater 是否忽略将来时间常用表示
     * @return ignoreLater如果为true，timestamp如果是将来时间则使用修正的表示方式（“刚刚”）。
     */
    public  String getTimeIntervalIntroForDateTime(String datetime, boolean ignoreLater) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar curCalendar = Calendar.getInstance();
        //如果需要转换的时间比当前时间晚,且忽略将来时间表示
        if (calendar.after(curCalendar) && ignoreLater) {
            return "刚刚";
        }
        //和当前时间的年份和月份是否相同
        if (calendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR) && calendar.get
                (Calendar.MONTH) ==
                curCalendar.get(Calendar.MONTH)) {
            //年月日是否都相同
            int day1 = calendar.get(Calendar.DAY_OF_YEAR);
            int day2 = curCalendar.get(Calendar.DAY_OF_YEAR);
            if (day1 < day2) {//以前
                return (day2 - day1) + "天前";
            } else if (day1 > day2) {//以后
                return (day1 - day2) + "天后";
            } else {
                int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
                int hour2 = curCalendar.get(Calendar.HOUR_OF_DAY);
                if (hour1 < hour2) {//以前
                    return (hour2 - hour1) + "小时前";
                } else if (hour1 > hour2) {//以后
                    return (hour1 - hour2) + "小时后";
                } else {
                    int minute1 = calendar.get(Calendar.MINUTE);
                    int minute2 = curCalendar.get(Calendar.MINUTE);
                    if (minute1 < minute2) {//以前
                        return (minute2 - minute1) + "分钟前";
                    } else if (minute1 > minute2) {//以后
                        return (minute1 - minute2) + "分钟后";
                    } else {
                        //忽略秒
                        return "刚刚";
                    }
                }
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("M-d");
            return sdf.format(calendar.getTime());
        }
    }

    /**
     * 返回时间间隔的常用表示
     *
     * @param timestamp
     * @return
     */
    public  String getTimeIntervalIntroForCollection(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        Calendar curCalendar = Calendar.getInstance();

        if (calendar.get(Calendar.DAY_OF_YEAR) == curCalendar.get(Calendar.DAY_OF_YEAR)) {
            Date d = new Date(timestamp * 1000);
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            return sf.format(d);
        } else if (calendar.get(Calendar.DAY_OF_YEAR) == (curCalendar.get(Calendar.DAY_OF_YEAR) - 1)) {
            return "昨天";
        } else if (calendar.get(Calendar.WEEK_OF_YEAR) == curCalendar.get(Calendar.WEEK_OF_YEAR)) {
            int week = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
            switch (week) {
                case 0:
                    return "星期天";
                case 1:
                    return "星期一";
                case 2:
                    return "星期二";
                case 3:
                    return "星期三";
                case 4:
                    return "星期四";
                case 5:
                    return "星期五";
                case 6:
                    return "星期六";
                default:
                    return "    ";
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.format(calendar.getTime());
        }
    }

    public static String getTimeIntervalIntroForHeadline(String datetime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar curCalendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_YEAR) == curCalendar.get(Calendar.DAY_OF_YEAR)) {
            Date d = curCalendar.getTime();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            return sf.format(d);
        } else if (calendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            return sdf.format(calendar.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(calendar.getTime());
        }
    }

    /**
     * 返回时间间隔的常用表示(消息中心)
     *
     * @param timestamp
     * @return
     */
    public  String getTimeIntervalIntroForMsgCenter(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        Calendar curCalendar = Calendar.getInstance();
        Date d = new Date(timestamp * 1000);
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        if (calendar.get(Calendar.DAY_OF_YEAR) == curCalendar.get(Calendar.DAY_OF_YEAR)) {
            return sf.format(d);
        } else if (calendar.get(Calendar.DAY_OF_YEAR) == curCalendar.get(Calendar.DAY_OF_YEAR) -
                1) {
            return "昨天 " + sf.format(d);
        } else if (calendar.get(Calendar.WEEK_OF_YEAR) == curCalendar.get(Calendar.WEEK_OF_YEAR)) {
            int week = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
            String weekStr = "";
            switch (week) {
                case 0:
                    weekStr = "星期天";
                    break;
                case 1:
                    weekStr = "星期一";
                    break;
                case 2:
                    weekStr = "星期二";
                    break;
                case 3:
                    weekStr = "星期三";
                    break;
                case 4:
                    weekStr = "星期四";
                    break;
                case 5:
                    weekStr = "星期五";
                    break;
                case 6:
                    weekStr = "星期六";
                    break;
                default:
                    weekStr = "";
                    break;
            }
            return weekStr + " " + sf.format(d);
        } else {
            SimpleDateFormat sdf = defaultDateTimeFormatNoSecond.get();
            return sdf.format(calendar.getTime());
        }
    }

    /**
     * 获得当天0点时间， 起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     * @return 当天0点时间 单位毫秒
     */
    public  long getTimesMorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获得当天24点时间， 起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     * @return 当天24点时的毫秒数
     */
    public  long getTimesNight(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获得本周一0点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     * @return  获得本周一0点时刻的毫秒数
     */
    public  long getTimesWeekMorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTimeInMillis();
    }

    /**
     * 获得本周日24点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     * @return 获得本周日24点时刻的毫秒数
     */
    public  long getTimesWeekNight(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime().getTime()+ (7 * 24 * 60 * 60 * 1000);
    }

    /**
     * 获得本月第一天0点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     * @return 获得本月第一天0点时刻的毫秒数
     */
    public  long getTimesMonthMorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return  cal.getTimeInMillis();
    }

    /**
     * 获得本月最后一天24点时间,起始时间为 1970-1-1 0:0分* 00:00:00.000 GMT 默认时区
     * @return 获得本月最后一天24点时间
     */
    public  long getTimesMonthNight(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return  cal.getTimeInMillis();
    }
}
