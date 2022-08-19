package com.heart.photobucket.aspect;

import com.heart.photobucket.common.Constants;
import com.heart.photobucket.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/10 10:19.
 * Editored:
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切面织入点
     */
    @Pointcut("execution(public * com.heart.photobucket.controller..*.*(..))")
    public void controllerLog() {
    }

    /**
     * slf4j线程常量切面 在每个请求线程里加上traceId
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.ROOT);
        MDC.put(Constants.FIELD_MDC_TRACE_ID, uuid);
        Object proceed = point.proceed();
        MDC.remove(Constants.FIELD_MDC_TRACE_ID);
        return proceed;
    }

    /**
     * 方法调用之前执行
     *
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Object[] args = joinPoint.getArgs();
        log.info("Request from :{}, request time :{}, request method :{}, request path :{}, request resource :{}, request params :{}", IpUtils.getIpAddr(request), LocalDateTime.now(), request.getMethod(), request.getRequestURL(), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), Arrays.toString(args));
    }

    /**
     * 方法调用完成时执行
     *
     * @param resp
     */
    @AfterReturning(returning = "resp", pointcut = "controllerLog()")
    public void doAfterReturning(Object resp) {
        log.info("Response :{}", resp);
    }

    /**
     * 方法抛出异常时执行，不在这里处理，交给SysExceptionHandler
     *
     * @param throwable
     */
//    @AfterThrowing(throwing = "throwable", pointcut = "controllerLog()")
//    public void doAfterThrowing(Throwable throwable) {
//        log.error("aspect throwable :{}", throwable.getMessage());
//    }
}
