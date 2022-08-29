package com.heart.photobucket.service;

import com.heart.photobucket.entity.Photo;
import com.heart.photobucket.exceptions.SysException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/13 0:10.
 * Editored:
 */
public interface BucketService {

    /**
     * 上传图片
     *
     * @param multipartFiles
     * @return
     * @throws SysException
     */
    Map<String, Object> upload(MultipartFile[] multipartFiles) throws SysException;

    /**
     * 查询图片列表
     *
     * @param photoStatus
     * @return
     * @throws SysException
     */
    List<Photo> queryPhotoList(Integer photoStatus) throws SysException;
}
