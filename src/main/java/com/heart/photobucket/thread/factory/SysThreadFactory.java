package com.heart.photobucket.thread.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * About: 自定义ThreadFactory
 * Other: 设置线程名
 * Created: lwf14 on 2022/8/21 21:44.
 * Editored:
 */
@Component
public class SysThreadFactory implements ThreadFactory {

    public static final Logger log = LoggerFactory.getLogger(SysThreadFactory.class);

    public static final String NAME_PREFIX = "sys-thread";

    public static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        String threadName = NAME_PREFIX + "-" + THREAD_NUMBER.getAndIncrement();
        log.debug(" *** custom thread created :{}", threadName);
        return new Thread(r,threadName);
    }
}
