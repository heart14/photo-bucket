package com.heart.photobucket.utils;

import com.heart.photobucket.common.Constants;
import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.model.SysResponse;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/9 16:53.
 * Editored:
 */
public class SysResponseUtils {

    public static SysResponse success() {
        return success(null);
    }


    public static SysResponse success(Object data) {
        return success(Constants.STATE_SUCCESS, ErrCodeEnums.SUCCESS.getCode(), ErrCodeEnums.SUCCESS.getMsg(), data);
    }

    public static SysResponse success(String state, Object data) {
        return success(Constants.STATE_SUCCESS + "[" + state + "]", ErrCodeEnums.SUCCESS.getCode(), ErrCodeEnums.SUCCESS.getMsg(), data);
    }

    public static SysResponse success(int code, String msg, Object data) {
        return new SysResponse(Constants.STATE_SUCCESS, code, msg, data);
    }

    public static SysResponse success(String state, int code, String msg, Object data) {
        return new SysResponse(state, code, msg, data);
    }

    public static SysResponse fail() {
        return fail(null);
    }

    public static SysResponse fail(Object data) {
        return fail(ErrCodeEnums.UNKNOWN_EXCEPTION.getCode(), ErrCodeEnums.UNKNOWN_EXCEPTION.getMsg(), data);
    }

    public static SysResponse fail(int code, String msg, Object data) {
        return fail(Constants.STATE_FAIL, code, msg, data);
    }

    public static SysResponse fail(String state, int code, String msg, Object data) {
        return new SysResponse(state, code, msg, data);
    }
}
