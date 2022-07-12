package com.heart.photobucket.exceptions;

import com.heart.photobucket.common.Constants;
import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.model.SysResponse;
import com.heart.photobucket.utils.SysResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/9 17:35.
 * Editored:
 */
@RestControllerAdvice
public class SysExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SysExceptionHandler.class);

    @ExceptionHandler({SysException.class})
    public SysResponse sysExceptionHandler(SysException e) {
        //手动抛出自定异常
        log.error("自定异常 :{}", e.getMessage(), e);
        Map<String, String> map = new HashMap<>();
        map.put(Constants.FIELD_MDC_TRACE_ID, MDC.get(Constants.FIELD_MDC_TRACE_ID));
        return SysResponseUtils.fail(e.getCode(), e.getMessage(), map);
    }

    @ExceptionHandler({Exception.class})
    public SysResponse exceptionHandler(Exception e) {
        //系统异常
        log.error("系统异常 :{}", e.getMessage(), e);
        Map<String, String> map = new HashMap<>();
        map.put(Constants.FIELD_MDC_TRACE_ID, MDC.get(Constants.FIELD_MDC_TRACE_ID));
        return SysResponseUtils.fail(ErrCodeEnums.SYSTEM_EXCEPTION.getCode(), ErrCodeEnums.SYSTEM_EXCEPTION.getMsg(), map);
    }


}
