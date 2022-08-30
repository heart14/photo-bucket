package com.heart.photobucket.service.impl;

import cn.hutool.core.lang.Assert;
import com.heart.photobucket.common.Constants;
import com.heart.photobucket.common.SysProperties;
import com.heart.photobucket.dao.PhotoMapper;
import com.heart.photobucket.entity.Photo;
import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.exceptions.SysException;
import com.heart.photobucket.service.BucketService;
import com.heart.photobucket.utils.DateUtils;
import com.heart.photobucket.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private final PhotoMapper photoMapper;

    public BucketServiceImpl(SysProperties sysProperties, PhotoMapper photoMapper) {
        this.sysProperties = sysProperties;
        this.photoMapper = photoMapper;
    }

    @Override
    public Map<String, Object> upload(MultipartFile[] multipartFiles) throws SysException {
        if (multipartFiles == null) {
            throw new SysException(ErrCodeEnums.PARAMS_EXCEPTION.getCode(), ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
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

        List<Photo> successList = new ArrayList<>();
        List<String> failList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {

            String contentType = multipartFile.getContentType();

            if (MediaType.IMAGE_JPEG_VALUE.equalsIgnoreCase(contentType) ||
                    MediaType.IMAGE_PNG_VALUE.equalsIgnoreCase(contentType) ||
                    MediaType.IMAGE_GIF_VALUE.equalsIgnoreCase(contentType)) {
                String originalFilename = multipartFile.getOriginalFilename();
                String filePath = currentPath + "/" + originalFilename;
                try {
                    multipartFile.transferTo(new File(filePath));

                    String fileUrl = sysProperties.getProperty("bucket.url") + "/" + date + "/" + originalFilename;

                    Photo photo = new Photo();
                    photo.setPhotoId(StringUtils.UuidLowerCase());
                    photo.setPhotoName(originalFilename);
                    photo.setPhotoDesc(originalFilename);
                    photo.setPhotoSource("");
                    photo.setPhotoTarget(filePath);
                    photo.setPhotoUrl(fileUrl);
                    photo.setPhotoSize(multipartFile.getSize());
                    photo.setPhotoStatus(Constants.STATUS_VALID);
                    photo.setCreateTime(new Date());

                    photoMapper.insert(photo);
                    successList.add(photo);
                } catch (IOException e) {
                    logger.warn("file upload fail :{}, {}", originalFilename, e.getMessage());
                    failList.add(originalFilename);
                }
            }
        }

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("successList", successList);
        resultMap.put("failList", failList);

        return resultMap;
    }

    @Override
    public void download(String photoId) throws SysException {

    }

    @Override
    public Photo queryPhotoById(String photoId) throws SysException {
        //Assert
        Assert.notBlank(photoId,ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
        Photo photo = photoMapper.selectByPhotoId(photoId);
        Assert.notNull(photo,ErrCodeEnums.RESULT_EXCEPTION.getMsg());
        return photo;
    }

    @Override
    public List<Photo> queryPhotoList(Integer photoStatus) throws SysException {
        return photoMapper.selectAllByPhotoStatus(photoStatus);
    }

}
