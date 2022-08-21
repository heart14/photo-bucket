package com.heart.photobucket.pool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * About: 线程池包装类
 * Other:
 * Created: lwf14 on 2022/8/21 14:34.
 * Editored:
 */
public class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {

    /**
     * ThreadPoolTaskExecutor与ThreadPoolExecutor的区别
     *      ThreadPoolExecutor：JDK提供的线程池类，（java.util.concurrent.ThreadPoolExecutor）继承自java.util.concurrent.Executor
     *      ThreadPoolTaskExecutor：Spring提供的线程池类（org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor），最终也是继承自java.util.concurrent.Executor
     */
}
