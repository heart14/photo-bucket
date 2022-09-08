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
    PARAMS_EXCEPTION(9003, "参数异常"),

    /**
     * 结果集异常，ErrorCode :9004
     */
    RESULT_EXCEPTION(9004, "查询异常"),

    /**
     * 字符集异常，ErrorCode :9005
     */
    CHARSET_EXCEPTION(9005, "字符集异常"),

    /**
     * 网络请求异常，ErrorCode :9006
     */
    HTTP_EXCEPTION(9006, "网络请求异常");

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

    /**
     * 根据msg值获取ErrCodeEnums
     *
     * @param msg
     * @return
     */
    public static ErrCodeEnums fromMsgString(String msg) {
        for (ErrCodeEnums errCodeEnums : ErrCodeEnums.values()) {
            if (errCodeEnums.getMsg().equals(msg)) {
                return errCodeEnums;
            }
        }
        return null;
    }

    /**
     * 根据code值获取ErrCodeEnums
     *
     * @param code
     * @return
     */
    public static ErrCodeEnums fromCode(int code) {
        for (ErrCodeEnums errCodeEnums : ErrCodeEnums.values()) {
            if (errCodeEnums.getCode() == code) {
                return errCodeEnums;
            }
        }
        return null;
    }
}
