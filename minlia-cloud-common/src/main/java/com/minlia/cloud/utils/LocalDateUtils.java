package com.minlia.cloud.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author garen
 * @description: java8时间工具类
 * @date:2018/7/16
 * @version:V1.0
 */
public final class LocalDateUtils {

    //默认使用系统当前时区
    private static final ZoneId ZONE = ZoneId.systemDefault();

    //东八区
//            ZoneOffset zoneOffset = ZoneOffset.of("+8");
    private static final ZoneOffset ZONE_OFF_SET = ZoneOffset.ofHours(8);

    private static final String DATE_FORMAT = "yyyy-MM-dd";


    private static final String TIME_NOFUII_FORMAT = "yyyyMMddHHmmss";

    private static final String REGEX = "\\:|\\-|\\s";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);


    /**
     * 根据传入的时间格式返回系统当前的时间
     *
     * @return
     */
    public static String format() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    /**
     * 根据传入的时间格式返回系统当前的时间
     *
     * @param pattern
     * @return
     */
    public static String format(String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return now.format(dateTimeFormatter);
    }

    /**
     * 根据传入的时间格式返回系统当前的时间
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 将Date转换成LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
//        return Instant.ofEpochMilli(date.getTime()).atZone(ZONE).toLocalDateTime();
        return LocalDateTime.ofInstant(date.toInstant(), ZONE);
    }

    /**
     * 将Date转换成LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     * 将Date转换成LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        return dateToLocalDateTime(date).toLocalTime();
    }

    /**
     * 将LocalDate转换成Date
     *
     * @param localDate
     * @return date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZONE).toInstant());
    }

    /**
     * 将LocalDateTime转换成Date
     *
     * @param localDateTime
     * @return date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZONE).toInstant());
    }

    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZONE_OFF_SET).toEpochMilli();
    }

    public static long localDateToTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay().toInstant(ZONE_OFF_SET).toEpochMilli();
    }

    public static LocalDateTime timestampTolocalDateTime(long timestamp) {
//        return LocalDateTime.ofEpochSecond(jsonParser.getLongValue() / 1000, 0, ZoneOffset.ofHours(8));
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZONE_OFF_SET);
    }

    public static LocalDate timestampTolocalDate(long timestamp) {
        return timestampTolocalDateTime(timestamp).toLocalDate();
    }

    public static LocalTime timestampTolocalTime(long timestamp) {
        return timestampTolocalDateTime(timestamp).toLocalTime();
    }


    /**
     * 将相应格式yyyy-MM-dd yyyy-MM-dd HH:mm:ss 时间字符串转换成Date
     *
     * @param time   string
     * @param format string
     * @return date
     */
    public static Date stringToDate(String time, String format) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format);
        if (DEFAULT_DATE_TIME_FORMAT.equals(format)) {
            return LocalDateUtils.localDateTimeToDate(LocalDateTime.parse(time, f));
        } else if (DATE_FORMAT.equals(format)) {
            return LocalDateUtils.localDateToDate(LocalDate.parse(time, f));
        }
        return null;
    }

    /**
     * time 类型等于Date返回String time 类型等于String返回对应格式化日期类型
     * time 等于String 暂时支持format为yyyy-MM-dd. yyyy-MM-dd HH:mm:ss. yyyyMMddHHmmss
     * time 等于Date 不限制格式化类型，返回String
     *
     * @param time   string or date
     * @param format string
     * @param <T>    T
     * @return localDateTime or localDate or string
     */
    public static <T> T timeFormat(T time, String format) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format);
        //暂时支持三种格式转换
        if (ClassIdentical.isCompatible(String.class, time)) {
            if (DEFAULT_DATE_TIME_FORMAT.equals(format)) {
                LocalDateTime localDateTime = LocalDateTime.parse(time.toString(), f);
                return (T) localDateTime.atZone(ZONE).toInstant();
            }
            if (DATE_FORMAT.equals(format)) {
                LocalDate localDate = LocalDate.parse(time.toString(), f);
                return (T) localDate;
            }
            if (TIME_NOFUII_FORMAT.equals(format)) {
                String rp = time.toString().replaceAll(REGEX, "");
                LocalDateTime localDate = LocalDateTime.parse(time.toString(), f);
                return (T) localDate;
            }
        }
        if (ClassIdentical.isCompatible(Date.class, time)) {
            Date date = (Date) time;
            Instant instant = date.toInstant();
            instant.atZone(ZONE).format(f);
            return (T) instant.atZone(ZONE).format(f);
        }
        return null;
    }

    /**
     * 根据ChronoUnit枚举计算两个时间差，日期类型对应枚举
     * 注:注意时间格式，避免cu选择不当的类型出现的异常
     *
     * @param cu chronoUnit.enum.key
     * @param d1 date
     * @param d2 date
     * @return
     */
    public static long chronoUnitBetweenByDate(ChronoUnit cu, Date d1, Date d2) {
        return cu.between(LocalDateUtils.dateToLocalDateTime(d1), LocalDateUtils.dateToLocalDateTime(d2));
    }

    /**
     * 根据ChronoUnit枚举计算两个时间差，日期类型对应枚举
     * 注:注意时间格式，避免cu选择不当的类型出现的异常
     *
     * @param cu chronoUnit.enum.key
     * @param s1 string
     * @param s2 string
     * @return
     */
    public static Long chronoUnitBetweenByString(ChronoUnit cu, String s1, String s2, String dateFormat) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(dateFormat);
        if (DEFAULT_DATE_TIME_FORMAT.equals(dateFormat)) {
            LocalDateTime l1 = LocalDateUtils.dateToLocalDateTime(LocalDateUtils.stringToDate(s1, dateFormat));
            LocalDateTime l2 = LocalDateUtils.dateToLocalDateTime(LocalDateUtils.stringToDate(s2, dateFormat));
            return cu.between(l1, l2);
        }
        if (DATE_FORMAT.equals(dateFormat)) {
            LocalDate l1 = LocalDateUtils.dateToLocalDate(LocalDateUtils.stringToDate(s1, dateFormat));
            LocalDate l2 = LocalDateUtils.dateToLocalDate(LocalDateUtils.stringToDate(s2, dateFormat));
            return cu.between(l1, l2);
        }
        if (TIME_NOFUII_FORMAT.equals(dateFormat)) {
            LocalDateTime l1 = LocalDateTime.parse(s1.replaceAll(REGEX, ""), f);
            LocalDateTime l2 = LocalDateTime.parse(s2.replaceAll(REGEX, ""), f);
            return cu.between(l1, l2);
        }
        return null;
    }

    /**
     * 根据ChronoUnit枚举计算两个时间相加，日期类型对应枚举
     * 注:注意时间格式，避免cu选择不当的类型出现的异常
     *
     * @param cu chronoUnit.enum.key
     * @param d1 date
     * @param d2 long
     * @return
     */
    public static Date chronoUnitPlusByDate(ChronoUnit cu, Date d1, long d2) {
        return LocalDateUtils.localDateTimeToDate(cu.addTo(LocalDateUtils.dateToLocalDateTime(d1), d2));
    }

    /**
     * 将time时间转换成毫秒时间戳
     *
     * @param time string
     * @return
     */
    public static long stringDateToMilli(String time) {
        return LocalDateUtils.stringToDate(time, DEFAULT_DATE_TIME_FORMAT).toInstant().toEpochMilli();
    }

    /**
     * 将毫秒时间戳转换成Date
     *
     * @param time string
     * @return
     */
    public static Date timeMilliToDate(String time) {
        return Date.from(Instant.ofEpochMilli(Long.parseLong(time)));
    }
}