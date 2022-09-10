package com.heart.photobucket.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/9 16:27.
 * Editored:
 */
@ApiModel("请求响应")
public class SysResponse implements Serializable {

    private static final long serialVersionUID = -1208864543584339177L;

    @ApiModelProperty(name = "state",notes = "状态值：SUCCESS|FAIL")
    private String state;

    @ApiModelProperty(name = "code",notes = "返回码")
    private Integer code;

    @ApiModelProperty(name = "msg",notes = "返回信息")
    private String msg;

    @ApiModelProperty(name = "data",notes = "返回数据")
    private Object data;

    public SysResponse() {
    }

    public SysResponse(String state, Integer code, String msg, Object data) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SysResponse{" +
                "state=" + state +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
