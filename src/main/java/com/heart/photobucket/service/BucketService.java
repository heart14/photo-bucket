package com.heart.photobucket.service;

import com.heart.photobucket.exceptions.SysException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/13 0:10.
 * Editored:
 */
public interface BucketService {

    Map<String, Object> upload(MultipartFile[] multipartFiles) throws SysException;
}
