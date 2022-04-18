package com.heart.photobucket.common;

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
@Component
public class SysProperties {

    public static Properties properties = new Properties();
    public static InputStream inputStream = SysProperties.class.getResourceAsStream("/system.properties");

    static{
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String props){
        return properties.getProperty(props);
    }
}
