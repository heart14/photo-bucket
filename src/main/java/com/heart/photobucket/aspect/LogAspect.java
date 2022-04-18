package com.heart.photobucket.aspect;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.heart.photobucket.model.SysRequest;
import com.heart.photobucket.model.SysResponse;
import com.heart.photobucket.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

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
     * 方法调用之前执行
     *
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof SysRequest) {
            SysRequest arg = (SysRequest) args[0];
            log.info("[{}] Request from :{}, request time :{}, request method :{}, request path :{}, request resource :{}, request params :{}", arg.getBizSeq(), IpUtils.getIpAddr(request), LocalDateTime.now(), request.getMethod(), request.getRequestURL(), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), Arrays.toString(args));
        }else {
            String sysRequest = request.getParameter("sysRequest");
            SysRequest bean = JSONUtil.toBean(sysRequest, SysRequest.class);
            log.info("[{}] Request from :{}, request time :{}, request method :{}, request path :{}, request resource :{}, request params :{}", bean.getBizSeq(), IpUtils.getIpAddr(request), LocalDateTime.now(), request.getMethod(), request.getRequestURL(), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), bean);
        }
    }

    /**
     * 方法调用完成时执行
     *
     * @param resp
     */
    @AfterReturning(returning = "resp", pointcut = "controllerLog()")
    public void doAfterReturning(Object resp) {
        if (resp instanceof SysResponse) {
            SysResponse sysResponse = (SysResponse) resp;
            log.info("[{}] Response :{}", sysResponse.getState(), sysResponse);
        } else {
            log.info("[{}] Response :{}", "-", resp);
        }
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
