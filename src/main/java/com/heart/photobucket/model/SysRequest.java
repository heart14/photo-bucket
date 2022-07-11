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

    @ApiModelProperty("请求ID")
    private String traceId;

    @ApiModelProperty("请求命令")
    private String cmd;

    @ApiModelProperty("请求参数")
    private String biz;

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

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
//
//        if (traceId == null || traceId.trim().length() == 0) {
//            this.traceId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
//        }
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    @Override
    public String toString() {
        return "SysRequest{" +
                "ver='" + ver + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", bizCmd='" + cmd + '\'' +
                ", bizSeq='" + traceId + '\'' +
                ", bizParam='" + biz + '\'' +
                '}';
    }
}
