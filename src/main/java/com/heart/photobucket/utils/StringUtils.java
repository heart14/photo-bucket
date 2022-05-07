package com.heart.photobucket.utils;

import java.util.UUID;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/28 23:03.
 * Editored:
 */
public class StringUtils {

    public static String Uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
