package com.heart.photobucket.dao;

import com.heart.photobucket.entity.Photo;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
}