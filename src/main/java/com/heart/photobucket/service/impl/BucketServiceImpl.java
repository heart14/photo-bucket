package com.heart.photobucket.service.impl;

import com.heart.photobucket.common.Constants;
import com.heart.photobucket.common.SysProperties;
import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.exceptions.SysException;
import com.heart.photobucket.service.BucketService;
import com.heart.photobucket.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/13 0:11.
 * Editored:
 */
@Service
public class BucketServiceImpl implements BucketService {

    private static final Logger logger = LoggerFactory.getLogger(BucketServiceImpl.class);

    private final SysProperties sysProperties;

    public BucketServiceImpl(SysProperties sysProperties) {
        this.sysProperties = sysProperties;
    }

    @Override
    public Map<String, List<String>> upload(MultipartFile[] multipartFiles) throws SysException {
        if (multipartFiles == null) {
            throw new SysException(Constants.STATE_FAIL, ErrCodeEnums.PARAMS_EXCEPTION.getCode(), ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
        }

        //获取配置文件中图片上传目录
        String bucketPath = sysProperties.getProperty("bucket.path");

        String date = DateUtils.currentDate("yyyyMMdd");

        File bucketDir = new File(bucketPath);
        if (!bucketDir.exists()) {
            boolean b = bucketDir.mkdirs();
            logger.info("bucket dir create {}!", b ? "success" : "fail");
        }

        String currentPath = bucketPath + "/" + date;
        File currentDir = new File(currentPath);
        if (!currentDir.exists()) {
            boolean b = currentDir.mkdirs();
            logger.info("current dir create {}!", b ? "success" : "fail");
        }

        List<String> successList = new ArrayList<>();
        List<String> failList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String filePath = currentPath + "/" + multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(filePath));

                String fileUrl = sysProperties.getProperty("bucket.url") + "/" + date + "/" + multipartFile.getOriginalFilename();
                successList.add(fileUrl);

            } catch (IOException e) {
                logger.warn("file upload fail :{}, {}", multipartFile.getOriginalFilename(), e.getMessage());
                failList.add(multipartFile.getOriginalFilename());
            }
        }

        Map<String, List<String>> resultMap = new HashMap<>(2);
        resultMap.put("successList", successList);
        resultMap.put("failList", failList);

        return resultMap;
    }
}
