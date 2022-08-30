package com.heart.photobucket.apitest;

import com.heart.photobucket.entity.Photo;
import com.heart.photobucket.service.BucketService;
import com.heart.photobucket.thread.pool.SysThreadPoolTaskExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * About: JUnit test
 * Other:
 * Created: lwf14 on 2022/8/30 21:31.
 * Editored:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    public static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Autowired
    private BucketService bucketService;

    @Autowired
    private SysThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void testChildThresd() {
        threadPoolTaskExecutor.execute(() -> log.info("test child thread."));
    }

    @Test
    public void testDb() {
        List<Photo> photos = bucketService.queryPhotoList(1);
        log.info("photo list :{}", photos);
    }
}
