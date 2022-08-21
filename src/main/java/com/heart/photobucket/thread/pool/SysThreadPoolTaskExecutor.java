package com.heart.photobucket.thread.pool;

import com.heart.photobucket.thread.utils.ThreadPoolMdcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * About: 封装线程池
 * Other:
 * Created: lwf14 on 2022/8/19 23:32.
 * Editored:
 */
@Component
public class SysThreadPoolTaskExecutor extends ThreadPoolTaskExecutorMdcWrapper {

    public static final Logger log = LoggerFactory.getLogger(SysThreadPoolTaskExecutor.class);

    @Override
    public void execute(Runnable task) {
        log.debug(" *** sysThreadPoolTaskExecutor executed Runnable task");
        //执行包装后的线程任务类
        super.execute(ThreadPoolMdcUtils.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        log.debug(" *** sysThreadPoolTaskExecutor submitted Runnable task");
        return super.submit(ThreadPoolMdcUtils.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        log.debug(" *** sysThreadPoolTaskExecutor submitted Callable<T> task");
        return super.submit(ThreadPoolMdcUtils.wrap(task, MDC.getCopyOfContextMap()));
    }

}
