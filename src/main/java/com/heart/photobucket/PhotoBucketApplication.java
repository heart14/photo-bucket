package com.heart.photobucket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.heart.photobucket.dao")
public class PhotoBucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoBucketApplication.class, args);
    }
}
