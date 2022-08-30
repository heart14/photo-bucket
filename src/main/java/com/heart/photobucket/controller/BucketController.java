package com.heart.photobucket.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.heart.photobucket.domain.PhotoVO;
import com.heart.photobucket.entity.Photo;
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

    private static final Logger log = LoggerFactory.getLogger(BucketController.class);

    private final BucketService bucketService;

    @Value("${server.port}")
    private String serverPort;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @ApiOperation("图片上传至图床")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public SysResponse uploadToBucket(@RequestParam("multipartFiles") MultipartFile[] multipartFiles) {
        Map<String, Object> result = bucketService.upload(multipartFiles);
        return SysResponseUtils.success(result);
    }

    //TODO 参数校验

    @ApiOperation("从图床下载图片")
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public SysResponse downloadFromBucket(@RequestBody SysRequest sysRequest) {
        String photoId = new JSONObject(sysRequest.getBiz()).getStr("photoId", "");
        Photo photo = bucketService.queryPhotoById(photoId);

        PhotoVO photoVO = new PhotoVO();
        BeanUtil.copyProperties(photo, photoVO);
        return SysResponseUtils.success(photoVO);
    }

}
