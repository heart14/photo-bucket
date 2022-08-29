package com.heart.photobucket.dao;

import com.heart.photobucket.entity.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoMapper {

    // default

    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    // custom

    /**
     * 查询所有记录
     *
     * @param photoStatus 图片状态
     * @return
     */
    List<Photo> selectAllByPhotoStatus(Integer photoStatus);
}