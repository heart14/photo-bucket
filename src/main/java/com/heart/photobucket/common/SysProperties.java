package com.heart.photobucket.common;

import com.heart.photobucket.config.SysPropertyLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/12 23:06.
 * Editored:
 */
public class SysProperties {

    public static final String BUCKET_PATH = SysPropertyLoader.getInstance().getSysProperty("bucket.path");
    public static final String BUCKET_URL = SysPropertyLoader.getInstance().getSysProperty("bucket.url");

    public static void main(String[] args) {
        System.out.println(BUCKET_PATH);
        System.out.println(BUCKET_URL);
    }
}
