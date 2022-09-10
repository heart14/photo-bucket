package com.heart.photobucket.controller;

import cn.hutool.json.JSONObject;
import com.heart.photobucket.entity.Photo;
import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.exceptions.SysException;
import com.heart.photobucket.model.SysRequest;
import com.heart.photobucket.model.SysResponse;
import com.heart.photobucket.service.BucketService;
import com.heart.photobucket.thread.pool.SysThreadPoolTaskExecutor;
import com.heart.photobucket.utils.HttpUtils;
import com.heart.photobucket.utils.SysResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final SysThreadPoolTaskExecutor threadPoolExecutor;
    private final BucketService bucketService;

    public TestController(SysThreadPoolTaskExecutor threadPoolExecutor, BucketService bucketService) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.bucketService = bucketService;
    }

    @ApiOperation("GET TEST")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public SysResponse get(String p1, int p2, boolean p3) {

        logger.info("test get ：p1 = {}, p2 = {}, p3 = {}", p1, p2, p3);
        threadPoolExecutor.execute(() -> logger.info("test child thread mdc trace id"));
        return SysResponseUtils.success("responsed by port :" + serverPort);
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
        threadPoolExecutor.execute(() -> logger.info("test child thread mdc trace id"));
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("POST FORM DATA TEST")
    @RequestMapping(value = "/post/form", method = RequestMethod.POST)
    public SysResponse form(String p1, int p2, MultipartFile p3) {

        logger.info("test post form data ：p1 = {}, p2 = {}, p3 = {}", p1, p2, p3);
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("POST URL ENCODED TEST")
    @RequestMapping(value = "/post/url", method = RequestMethod.POST)
    public SysResponse form(String p1, int p2) {

        logger.info("test post url encoded ：p1 = {}, p2 = {}", p1, p2);
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("exception test")
    @RequestMapping(value = "/exception", method = RequestMethod.POST)
    public SysResponse exception() {
        int a = 1 / 0;
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("checkedException test")
    @RequestMapping(value = "/exception/checked", method = RequestMethod.POST)
    public SysResponse checkedException() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new SysException(ErrCodeEnums.PARAMS_EXCEPTION.getCode(), ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
        }
        return SysResponseUtils.success("responsed by port :" + serverPort);
    }

    @ApiOperation("db test")
    @RequestMapping(value = "/db", method = RequestMethod.POST)
    public SysResponse db(@RequestBody SysRequest sysRequest) {
        Object biz = sysRequest.getBiz();
        Integer photoStatus = new JSONObject(biz).getInt("photoStatus", 0);
        List<Photo> photos = bucketService.queryPhotoList(photoStatus);
        return SysResponseUtils.success(photos);
    }

    @ApiOperation("http get test")
    @RequestMapping(value = "/http/get", method = RequestMethod.POST)
    public SysResponse httpGet() {
        String get = HttpUtils.doGet("http://www.sadli.xyz/photobucket/test/get");
        return SysResponseUtils.success(get);
    }

    @ApiOperation("http post test")
    @RequestMapping(value = "/http/post", method = RequestMethod.POST)
    public SysResponse httpPost() {
        String url = "http://www.sadli.xyz/photobucket/test/post/json";

        Map<String, String> map = new HashMap<>();
        map.put("ver", "v1.1.0");
        map.put("cmd", "test http post json");
        map.put("biz", "no");
        String post = HttpUtils.doPostJson(url,HttpUtils.mapToJsonStr(map));
        return SysResponseUtils.success(post);
    }
}
