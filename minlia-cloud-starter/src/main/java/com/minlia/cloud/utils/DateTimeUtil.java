/*
 * Copyright © 2016 Minlia (http://oss.minlia.com/license/framework/2016)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minlia.cloud.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtil {
    public static final String FMT_yyyyMMddHHmmssSa_12 = "yyyy-MM-dd KK:mm:ss.S a";
    public static final String FMT_yyyyMMddHHmmssSa = "yyyyMMdd";
    public static final String FMT_yyyyMMddHHmmssa_12 = "yyyy-MM-dd KK:mm:ss a";
    public static final String FMT_yyyyMMddHHmma_12 = "yyyy-MM-dd KK:mm a";
    public static final String FMT_yyyyMMddHHa_12 = "yyyy-MM-dd KK a";
    public static final String FMT_yyyyMMdda_12 = "yyyy-MM-dd a";
    public static final String FMT_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String FMT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String FMT_yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String FMT_yyyyMM = "yyyy-MM";
    public static final String FMT_yyyyMMdd = "yyyy-MM-dd";
    public static final String FMT_HHmmA_12 = "KK:mm a";
    public static final String FMT_HHmmAz_12 = "KK:mm a,z";
    public static final String FMT_HHmmAzzzz_12 = "KK:mm a,zzzz";
    public static final String FMT_HHmmssA_12 = "KK:mm:ss a";
    public static final String FMT_HHmmssAz_12 = "KK:mm:ss a,z";
    public static final String FMT_HHmmssAzzzz_12 = "KK:mm:ss a,zzzz";
    public static final String FMT_HHmmss = "HH:mm:ss";
    public static final String FMT_HHmmssS = "HH:mm:ss.S";
    public static final String FMT_HHmm = "HH:mm";
    public static final String FMT_HHmmz = "HH:mm,z";
    public static final String FMT_HHmmzzzz = "HH:mm,zzzz";
    public static final String FMT_WWMMDDYY_EN = "EEE,MMM d,''yyyy";
    public static final String FMT_WWMMDDYY_CN = "EEE,yyyy年MMMd号";
    public static final String FMT_MMDDYY_EN = "MMM d,''yyyy";
    public static final String FMT_MMDDYY_CN = "yyyy年MMMd号";
    public static final String FMT_WW = "EEE";
    private static final String[] formatStr = { "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd", "HH:mm:ss", "HH:mm:ss.S", "HH:mm", "HH:mm,z", "HH:mm,zzzz", "yyyy-MM-dd KK:mm:ss.S a", "yyyy-MM-dd KK:mm:ss a",
            "yyyy-MM-dd KK:mm a", "yyyy-MM-dd KK a", "yyyy-MM-dd a", "KK:mm a", "KK:mm a,z", "KK:mm a,zzzz", "KK:mm:ss a", "KK:mm:ss a,z", "KK:mm:ss a,zzzz", "yyyyMMdd" };

    public static String formatDateTimetoString(Date date, String formatStr) {
        String reStr = "";
        if ((date == null) || (formatStr == null) || (formatStr.trim().length() < 1)) {
            return reStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(formatStr);
        reStr = sdf.format(date);
        return reStr == null ? "" : reStr;
    }

    public static Date getSystemDate(String fmtstr) {
        try {
            return parseToDate(formatDateTimetoString(getSystemDate(), fmtstr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getSystemDate();
    }

    public static String formatDateTimetoString(Date date, String formatStr, Locale locale) {
        String reStr = "";
        if ((date == null) || (formatStr == null) || (locale == null) || (formatStr.trim().length() < 1)) {
            return reStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, locale);
        reStr = sdf.format(date);
        return reStr == null ? "" : reStr;
    }

    public static String formatDateTimetoString(String dateStr, String formatStr) throws Exception {
        String dStr = "";
        if ((dateStr != null) && (dateStr.trim().length() > 0) && (formatStr != null) && (formatStr.trim().length() > 0)) {
            dStr = formatDateTimetoString(parseToDate(dateStr), formatStr);
        }
        return dStr;
    }

    public static String formatDateTimetoString(String dateStr, String formatStr, Locale locale) throws Exception {
        String dStr = "";
        if ((dateStr != null) && (dateStr.trim().length() > 0) && (formatStr != null) && (formatStr.trim().length() > 0) && (locale != null)) {
            dStr = formatDateTimetoString(parseToDate(dateStr, locale), formatStr, locale);
        }
        return dStr;
    }

    public static Date parseToDate(String dateTimeStr, String formatStr) throws Exception {
        if ((dateTimeStr == null) || (formatStr == null) || (dateTimeStr.trim().length() < 1) || (formatStr.trim().length() < 1)) {
            throw new IllegalArgumentException("参数dateTimeStr、formatStr不能是null或空格串！");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        try {
            return sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            throw new Exception(e);
        }
    }

    public static Date parseToDate(String dateTimeStr, String formatStr, Locale locale) throws Exception {
        if ((dateTimeStr != null) && (formatStr != null) && (locale != null) && (dateTimeStr.trim().length() > 0) && (formatStr.trim().length() > 0)) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr, locale);
            try {
                return sdf.parse(dateTimeStr);
            } catch (ParseException e) {
                throw new Exception(e);
            }
        }
        throw new IllegalArgumentException("参数dateTimeStr、formatStr、locale不能是null或空格串！");
    }

    public static Date parseToDate(String dateTimeStr) throws Exception {
        if ((dateTimeStr == null) || (dateTimeStr.trim().length() < 1)) {
            throw new IllegalArgumentException("参数dateTimeSt不能是null或空格串！");
        }
        int formatStrLength = formatStr.length;
        int i = 0;

        for (i = 0; i < formatStrLength; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr[i]);
            try {
                return sdf.parse(dateTimeStr);
            } catch (ParseException localParseException) {
            }
        }
        throw new Exception("日期格式不正确！");
    }

    public static Date parseToDate(int year, int month, int day) throws Exception {
        if ((month < 1) || (month > 12) || (day < 1) || (day > 31)) {
            throw new IllegalArgumentException("参数不正确！");
        }
        String yearStr = String.valueOf(year);
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);

        return parseToDate(yearStr + "-" + monthStr + "-" + dayStr);
    }

    public static Date parseToDate(int year, int month, int day, int h, int m, int s) throws Exception {
        if ((month < 1) || (month > 12) || (day < 1) || (day > 31) || (h < 0) || (h > 23) || (m < 0) || (m > 60) || (s < 0) || (s > 60)) {
            throw new IllegalArgumentException("参数不正确！");
        }
        String yearStr = String.valueOf(year);
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);
        String hStr = String.valueOf(h);
        String mStr = String.valueOf(m);
        String sStr = String.valueOf(s);

        return parseToDate(yearStr + "-" + monthStr + "-" + dayStr + " " + hStr + ":" + mStr + ":" + sStr);
    }

    public static Date parseToDate(String dateTimeStr, Locale locale) throws Exception {
        if ((dateTimeStr == null) || (dateTimeStr.trim().length() < 1) || (locale == null)) {
            throw new IllegalArgumentException("参数dateTimeSt、locale不能是null或空格串！");
        }
        int formatStrLength = formatStr.length;
        int i = 0;
        SimpleDateFormat sdf = null;
        for (i = 0; i < formatStrLength; i++) {
            sdf = new SimpleDateFormat(formatStr[i], locale);
            try {
                return sdf.parse(dateTimeStr);
            } catch (ParseException localParseException) {
            }
        }
        throw new Exception("日期格式不正确！");
    }

    public static String formatDateTimetoString(String dateTimeStr) throws Exception {
        return formatDateTimetoString(dateTimeStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTimetoString(String dateTimeStr, Locale locale) throws Exception {
        return formatDateTimetoString(dateTimeStr, "yyyy-MM-dd HH:mm:ss", locale);
    }

    public static String formatDateTimetoString(Date dateTime) {
        return formatDateTimetoString(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTimetoString(Date dateTime, Locale locale) {
        return formatDateTimetoString(dateTime, "yyyy-MM-dd HH:mm:ss", locale);
    }

    public static String formatDatetoString(String dateStr) throws Exception {
        return formatDateTimetoString(dateStr, "yyyy-MM-dd");
    }

    public static String formatDatetoString(String dateStr, Locale locale) throws Exception {
        return formatDateTimetoString(dateStr, "yyyy-MM-dd", locale);
    }

    public static String formatDatetoString(Date d) {
        return formatDateTimetoString(d, "yyyy-MM-dd");
    }

    public static String formatDatetoString(Date d, Locale locale) {
        return formatDateTimetoString(d, "yyyy-MM-dd", locale);
    }

    public static String formatTimetoString(String dateTimeStr) throws Exception {
        return formatDateTimetoString(dateTimeStr, "HH:mm:ss");
    }

    public static String formatTimetoString(String dateTimeStr, Locale locale) throws Exception {
        return formatDateTimetoString(dateTimeStr, "HH:mm:ss", locale);
    }

    public static String formatTimetoString(Date dateTimeStr) {
        return formatDateTimetoString(dateTimeStr, "HH:mm:ss");
    }

    public static String formatTimetoString(Date dateTimeStr, Locale locale) {
        return formatDateTimetoString(dateTimeStr, "HH:mm:ss", locale);
    }

    public static int getYearOfDate(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(1);
    }

    public static int getMonthOfYear(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(2) + 1;
    }

    public static Date getDateByFirstDayOfMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static int getDayOfMonth(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(5);
    }

    public static int getDayOfYear(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(6);
    }

    public static int getDayOfWeek(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(7) - 1;
    }

    public static int getWeekOfMonth(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(4);
    }

    public static int getWeekOfYear(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(3);
    }

    public static int getHoursOfDay(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int hours = calendar.get(11);
        return hours;
    }

    public static int getHoursOfDay12(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int hours = calendar.get(10);
        return hours;
    }

    public static int getMinutesOfHour(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int minutes = calendar.get(12);

        return minutes;
    }

    public static int getSecondsOfMinute(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int seconds = calendar.get(13);

        return seconds;
    }

    public static int getMillisecondsOfSecond(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int millisecond = calendar.get(14);

        return millisecond;
    }

    public static long getTime(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        return d.getTime();
    }

    public static int compareTwoDate(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象！");
        }

        long dI1 = d1.getTime();
        long dI2 = d2.getTime();

        if (dI1 > dI2)
            return -1;
        if (dI1 < dI2) {
            return 1;
        }
        return 0;
    }

    public static long getMillisecondsOfTwoDate(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象！");
        }
        long dI1 = d1.getTime();
        long dI2 = d2.getTime();
        return dI1 - dI2;
    }

    public static double getSecondsOfTwoDate(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象！");
        }
        long i = getMillisecondsOfTwoDate(d1, d2);

        return i / 1000.0D;
    }

    public static double getMinutesOfTwoDate(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象！");
        }
        long millions = getMillisecondsOfTwoDate(d1, d2);
        return millions / 60.0D / 1000.0D;
    }

    public static double getHoursOfTwoDate(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象！");
        }
        long millions = getMillisecondsOfTwoDate(d1, d2);
        return millions / 60.0D / 60.0D / 1000.0D;
    }

    public static double getDaysOfTwoDate(Date d1, Date d2) {
        if ((d1 == null) || (d2 == null)) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象！");
        }
        long millions = getMillisecondsOfTwoDate(d1, d2);
        return millions / 24.0D / 60.0D / 60.0D / 1000.0D;
    }

    public static Date addTime(Date d, double times, int type) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        long qv = 1L;
        switch (type) {
        case 14:
            qv = 1L;
            break;
        case 13:
            qv = 1000L;
            break;
        case 12:
            qv = 60000L;
            break;
        case 10:
            qv = 3600000L;
            break;
        case 5:
            qv = 86400000L;
            break;
        case 6:
        case 7:
        case 8:
        case 9:
        case 11:
        default:
            throw new RuntimeException("时间类型不正确!type＝" + type);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        long milliseconds = Math.round(Math.abs(times) * qv);
        if (times > 0.0D) {
            for (; milliseconds > 0L; milliseconds -= 2147483647L) {
                if (milliseconds > 2147483647L)
                    calendar.add(14, 2147483647);
                else
                    calendar.add(14, (int) milliseconds);
            }
        } else {
            for (; milliseconds > 0L; milliseconds -= 2147483647L) {
                if (milliseconds > 2147483647L)
                    calendar.add(14, -2147483647);
                else {
                    calendar.add(14, -(int) milliseconds);
                }
            }
        }
        return calendar.getTime();
    }

    public static Date addYears(Date d, int years) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(1, years);
        return calendar.getTime();
    }

    public static Date addMonths(Date d, int months) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(2, months);
        return calendar.getTime();
    }

    public static Date addDays(Date d, int days) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(10, days * 24);
        return calendar.getTime();
    }

    public static Date addHours(Date d, int hours) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(10, hours);
        return calendar.getTime();
    }

    public static Date addMinutes(Date d, int minutes) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(12, minutes);
        return calendar.getTime();
    }

    public static Date addSeconds(Date d, int seconds) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(13, seconds);
        return calendar.getTime();
    }

    public static Date addMilliseconds(Date d, int milliseconds) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(14, milliseconds);
        return calendar.getTime();
    }

    public static Date setYearOfDate(Date d, int year) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(1, year);
        return calendar.getTime();
    }

    public static Date setMonthOfDate(Date d, int month) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(2, month);
        return calendar.getTime();
    }

    public static Date setDayOfDate(Date d, int day) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(5, day);
        return calendar.getTime();
    }

    public static Date setHourOfDate(Date d, int hour) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, hour);
        return calendar.getTime();
    }

    public static Date setMinuteOfDate(Date d, int minute) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(12, minute);
        return calendar.getTime();
    }

    public static Date setSecondOfDate(Date d, int second) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(13, second);
        return calendar.getTime();
    }

    public static Date setMillisecondOfDate(Date d, int millisecond) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象！");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(14, millisecond);
        return calendar.getTime();
    }

    public static int getDaysOfMonth(Date d) {
        int year = getYearOfDate(d);
        int month = getMonthOfYear(d);
        return getDaysOfMonth(year, month);
    }

    public static int getDaysOfMonth(int year, int month) {
        int days = 0;
        if (month == 2) {
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
                days = 29;
            else {
                days = 28;
            }
        }
        if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            days = 30;
        }
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
            days = 31;
        }
        return days;
    }

    public static Date getSystemDate() {
        return new Date(System.currentTimeMillis());
    }

    public static long getSystemTime() {
        return System.currentTimeMillis();
    }

    public static Date getLastDay(Date date) {
        long day = date.getTime();
        long lastDay = day - 86400000L;
        return new Date(lastDay);
    }

    public static Date getTomorrow(Date date) {
        long day = date.getTime();
        long tomorrow = day + 86400000L;
        return new Date(tomorrow);
    }

    public static Date getDayLastMonth() {
        long day = new Date().getTime();
        long dayLastMonth = day - 1728000000L;
        return new Date(dayLastMonth);
    }

    public static Date getDayNextMonth() {
        long day = new Date().getTime();
        long dayNextMonth = day + 1728000000L;
        return new Date(dayNextMonth);
    }

    public static int getMonthCount(Date sDate, Date eDate) {
        String sDateStr = formatDateTimetoString(sDate, "MM");
        String eDateStr = formatDateTimetoString(eDate, "MM");
        int monthCount = Integer.parseInt(eDateStr) - Integer.parseInt(sDateStr) + 1;
        return monthCount;
    }

    public static int getYearCount(Date sDate, Date eDate) {
        String sDateStr = formatDateTimetoString(sDate, "yyyy");
        String eDateStr = formatDateTimetoString(eDate, "yyyy");
        return Integer.parseInt(eDateStr) - Integer.parseInt(sDateStr);
    }

    public static Date getDayNextMonth(Date date) {
        String yearStr = formatDateTimetoString(date, "yyyy");
        String monthStr = formatDateTimetoString(date, "MM");
        String dayStr = formatDateTimetoString(date, "dd");
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        if (month == 12) {
            month = 1;
            year++;
            yearStr = String.valueOf(year);
            monthStr = String.valueOf(month);
        }

        String dateStr = yearStr + "-" + monthStr + "-" + dayStr;
        try {
            date = parseToDate(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getCurrentMouthStart() {
        Date d = getSystemDate();
        d = setDayOfDate(d, 1);
        d = setHourOfDate(d, 0);
        d = setMinuteOfDate(d, 0);
        d = setSecondOfDate(d, 0);
        return d;
    }

    public static Date getDateNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, 1);
        return cal.getTime();
    }

    public static Integer daysDifference(Date date) {
        long l1 = get24HourMill(date);
        long l2 = get24HourMill(new Date());
        return Integer.valueOf((int) ((l2 - l1) / 86400L / 1000L));
    }

    private static long get24HourMill(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static Date getZeroDate(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    private static String otherDiff(Date startDate, Date endDate) {
        String[] type = { "年", "个月", "星期", "天", "小时", "分钟", "秒", "秒" };
        Object[] obj = timeDifference(startDate, endDate);
        String value = "1秒前";
        for (int i = 0; i < obj.length; i++) {
            if (!"0".equals(obj[i].toString())) {
                value = obj[i].toString() + type[i] + "前";
                break;
            }
        }
        return value;
    }

    public static String dynDiff(Date startDate, Date endDate) {
        String startDay = DateFormatUtils.format(startDate, "dd");
        String endtDay = DateFormatUtils.format(endDate, "dd");
        String value = "";
        if (startDay.equals(endtDay))
            value = DateFormatUtils.format(startDate, " HH:mm");
        else {
            value = otherDiff(startDate, endDate);
        }
        return value;
    }

    public static String resDiff(Date startDate, Date endDate) {
        Object[] obj = timeDifference(startDate, endDate);
        String value = "";
        if (Long.parseLong(obj[3].toString()) > 7L)
            value = DateFormatUtils.format(startDate, "yyyy-MM-dd HH:mm");
        else {
            value = otherDiff(startDate, endDate);
        }
        return value;
    }

    private static Object[] timeDifference(Date startTime, Date endTime) {
        if ((startTime == null) || (endTime == null)) {
            return new Object[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) };
        }
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startTime);
        end.setTime(endTime);

        long startMs = start.getTimeInMillis();
        long endMs = end.getTimeInMillis();
        long l_differ = endMs - startMs;
        long ll_differ = l_differ / 1000L;

        long second_differ = l_differ / 1000L;

        long year_differ = second_differ / 31536000L;
        long month_differ = second_differ / 2592000L;
        long week_differ = second_differ / 604800L;

        long day_differ = second_differ / 86400L;
        second_differ -= day_differ * 60L * 60L * 24L;
        long hour_differ = second_differ / 3600L;
        second_differ -= hour_differ * 60L * 60L;
        long minute_differ = second_differ / 60L;
        second_differ -= minute_differ * 60L;

        return new Object[] { Long.valueOf(year_differ), Long.valueOf(month_differ), Long.valueOf(week_differ), Long.valueOf(day_differ), Long.valueOf(hour_differ), Long.valueOf(minute_differ), Long.valueOf(second_differ), Long.valueOf(ll_differ) };
    }
}
