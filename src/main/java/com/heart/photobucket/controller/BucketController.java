package com.heart.photobucket.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.heart.photobucket.model.SysRequest;
import com.heart.photobucket.model.SysResponse;
import com.heart.photobucket.service.BucketService;
import com.heart.photobucket.utils.SysResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/4/1 17:00.
 * Editored:
 */
@Api(tags = "图床")
@RestController
@RequestMapping("/bucket")
public class BucketController {

    @Value("${server.port}")
    private String serverPort;

    private static final Logger logger = LoggerFactory.getLogger(BucketController.class);

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @ApiOperation("图片上传至图床")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public SysResponse uploadToBucket(@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {

        Map<String, List<String>> upload = bucketService.upload(multipartFiles);

        String parameter = request.getParameter("sysRequest");
        Assert.notBlank(parameter);
        SysRequest sysRequest = JSONUtil.toBean(parameter, SysRequest.class);

        return SysResponseUtils.success(sysRequest.getBizSeq(), upload);
    }

    @ApiOperation("从图床下载图片")
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public SysResponse downloadFromBucket(@RequestBody SysRequest sysRequest) {

        logger.info("test proxy balance ：{}", sysRequest);

        return SysResponseUtils.success(sysRequest.getBizSeq(), "responsed by port :" + serverPort);
    }
}
