package com.heart.photobucket.enums;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/9 17:00.
 * Editored:
 */
public enum ErrCodeEnums {

    /**
     * 成功，ErrorCode :9999
     */
    SUCCESS(9999, "请求成功"),

    /**
     * 系统异常，ErrorCode :9001
     */
    UNKNOWN_EXCEPTION(9001, "未知异常"),

    /**
     * 未知异常，ErrorCode :9002
     */
    SYSTEM_EXCEPTION(9002, "系统异常"),

    /**
     * 参数异常，ErrorCode :9003
     */
    PARAMS_EXCEPTION(9003, "参数异常");

    private Integer code;

    private String msg;

    ErrCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
