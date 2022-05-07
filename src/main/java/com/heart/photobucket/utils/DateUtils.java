package com.heart.photobucket.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/12 23:25.
 * Editored:
 */
public class DateUtils {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期字符串（默认格式）
     *
     * @return
     */
    public static String currentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
    }

    /**
     * 获取当前日期字符串（指定格式）
     *
     * @param pattern
     * @return
     */
    public static String currentDate(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间字符串（默认格式）
     *
     * @return
     */
    public static String currentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN));
    }

    /**
     * 获取当前时间字符串（指定格式）
     *
     * @param pattern
     * @return
     */
    public static String currentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String currentTimeMillis() {
        return String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
