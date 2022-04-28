package com.heart.photobucket.dao;

import com.heart.photobucket.entity.Photo;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/4/26 22:34.
 * Editored:
 */
public interface PhotoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
}