package com.heart.photobucket.domain;

import java.util.Date;

/**
 * About:
 * Other:
 * Created: lwf14 on 2022/8/30 22:12.
 * Editored:
 */
public class PhotoVO {


    private String photoId;

    private String photoName;

    private String photoDesc;

    private String photoUrl;

    private Long photoSize;

    private Integer photoWidth;

    private Integer photoHeight;

    private Integer photoStatus;

    private Date createTime;

    private Date updateTime;

    public PhotoVO() {
    }

    public PhotoVO(String photoId, String photoName, String photoDesc, String photoUrl, Long photoSize, Integer photoWidth, Integer photoHeight, Integer photoStatus, Date createTime, Date updateTime) {
        this.photoId = photoId;
        this.photoName = photoName;
        this.photoDesc = photoDesc;
        this.photoUrl = photoUrl;
        this.photoSize = photoSize;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
        this.photoStatus = photoStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getPhotoSize() {
        return photoSize;
    }

    public void setPhotoSize(Long photoSize) {
        this.photoSize = photoSize;
    }

    public Integer getPhotoWidth() {
        return photoWidth;
    }

    public void setPhotoWidth(Integer photoWidth) {
        this.photoWidth = photoWidth;
    }

    public Integer getPhotoHeight() {
        return photoHeight;
    }

    public void setPhotoHeight(Integer photoHeight) {
        this.photoHeight = photoHeight;
    }

    public Integer getPhotoStatus() {
        return photoStatus;
    }

    public void setPhotoStatus(Integer photoStatus) {
        this.photoStatus = photoStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PhotoVO{" +
                "photoId='" + photoId + '\'' +
                ", photoName='" + photoName + '\'' +
                ", photoDesc='" + photoDesc + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", photoSize=" + photoSize +
                ", photoWidth=" + photoWidth +
                ", photoHeight=" + photoHeight +
                ", photoStatus=" + photoStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
