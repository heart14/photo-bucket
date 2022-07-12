package com.heart.photobucket.controller;

import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.exceptions.SysException;
import com.heart.photobucket.model.SysRequest;
import com.heart.photobucket.model.SysResponse;
import com.heart.photobucket.utils.SysResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/7/11 17:44.
 * Editored:
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private String serverPort;

    @ApiOperation("GET TEST")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(String p1, int p2, boolean p3) {

        logger.info("test get ：p1 = {}, p2 = {}, p3 = {}", p1, p2, p3);
        return "success";
//        try{
//
//        int a = 1/0;
//        }catch (Exception e){
//            throw new SysException(ErrCodeEnums.PARAMS_EXCEPTION.getCode(), ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
//        }
//
//        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("GET PATH VARIABLE TEST")
    @RequestMapping(value = "/get/{p1}/{p2}", method = RequestMethod.GET)
    public SysResponse getPathVariable(@PathVariable String p1, @PathVariable int p2) {

        logger.info("test get ：p1 = {}, p2 = {}", p1, p2);
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("POST JSON TEST")
    @RequestMapping(value = "/post/json", method = RequestMethod.POST)
    public SysResponse json(@RequestBody SysRequest sysRequest) {

        logger.info("test post json ：{}", sysRequest);
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("POST FORM DATA TEST")
    @RequestMapping(value = "/post/form", method = RequestMethod.POST)
    public SysResponse form(String p1, int p2, MultipartFile p3) {

        logger.info("test post form data ：p1 = {}, p2 = {}, p3 = {}", p1, p2, p3);
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

}
