package com.minlia.cloud.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class RequestIdGenerator {


    public static final String DEFAULT_ORDER_NUMBER_PREFIX = "";


    public static String generateYearMonthDay() {
        return generateOrderNumberYearMonthDay("");
    }

    public static String generateOrderNumberYearMonthDay(String prefix) {
        String current = DateTimeUtil.formatDateTimetoString(new Date(), "yyyyMMddHHmmss");
        if (StringUtils.isNotEmpty(prefix)) {
            return prefix + current + RandomStringUtils.randomNumeric(6);
        } else {
            return current + RandomStringUtils.randomNumeric(6);
        }
    }



    public static String generateTimestamp(String prefix) {
        String generated= String.valueOf(System.currentTimeMillis());
        if (StringUtils.isNotEmpty(prefix)) {
            generated=prefix+generated;
        }
        return generated;
    }


    public static String generateRequestId() {
        return RandomStringUtils.randomAlphanumeric(32)+generateOrderNumberYearMonthDay(DEFAULT_ORDER_NUMBER_PREFIX);
    }



}
