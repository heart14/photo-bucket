package com.heart.photobucket.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.UUID;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/9 16:26.
 * Editored:
 */
@ApiModel("请求参数")
public class SysRequest implements Serializable {

    private static final long serialVersionUID = -3026896682454037934L;

    @ApiModelProperty("版本")
    private String ver;

    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("时间戳")
    private Long timestamp;

    @ApiModelProperty("请求命令")
    private String bizCmd;

    @ApiModelProperty("请求ID")
    private String bizSeq;

    @ApiModelProperty("请求参数")
    private String bizParam;

    public SysRequest() {
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBizCmd() {
        return bizCmd;
    }

    public void setBizCmd(String bizCmd) {
        this.bizCmd = bizCmd;
    }

    public String getBizSeq() {
        return bizSeq;
    }

    public void setBizSeq(String bizSeq) {
        this.bizSeq = bizSeq;

        if (bizSeq == null || bizSeq.trim().length() == 0) {
            this.bizSeq = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        }
    }

    public String getBizParam() {
        return bizParam;
    }

    public void setBizParam(String bizParam) {
        this.bizParam = bizParam;
    }

    @Override
    public String toString() {
        return "SysRequest{" +
                "ver='" + ver + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", bizCmd='" + bizCmd + '\'' +
                ", bizSeq='" + bizSeq + '\'' +
                ", bizParam='" + bizParam + '\'' +
                '}';
    }
}
