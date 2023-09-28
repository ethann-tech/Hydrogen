package com.ethan.hydrogen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DateUtil2 {
    private DateUtil2() {
        throw new IllegalArgumentException("该类不允许实例化");
    }

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

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.format(date);
    }

    public static String formatDate(long date, String format) {
        return formatDate(new Date(date), format);
    }

    public static long parseDateToMilliseconds(String date, String format) {
        try {
            return parseDate(date,format).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Date parseDate(String date,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    public static long convertToSeconds(Date date) {
        return date.getTime() / 1000L;
    }

    public static long convertToMinutes(Date date) {
        return convertToSeconds(date) / 60L;
    }

    public static long convertToHours(Date date) {
        return convertToMinutes(date) / 60L;
    }

    public static int getYear(Date date) {
        return getYear(date, Locale.CHINA);
    }

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

    public static List<String> findDates(String dayBegin,String formatBegin,String dayEnd,String formatEnd,String formatResult){
        Date dateBegin = parseDate(dayBegin,formatBegin);
        Date dateEnd = parseDate(dayEnd,formatEnd);
       return findDates(dateBegin,dateEnd).stream().map(date -> formatDate(date,formatResult)).collect(Collectors.toList());

    }



}
