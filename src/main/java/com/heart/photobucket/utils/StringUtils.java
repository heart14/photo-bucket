package com.heart.photobucket.utils;

import java.util.UUID;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/28 23:03.
 * Editored:
 */
public class StringUtils extends org.springframework.util.StringUtils {

    public static String UuidUpperCase() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String UuidLowerCase() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
