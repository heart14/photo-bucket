package com.heart.photobucket.exceptions;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/9 17:38.
 * Editored:
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 29695942793488621L;

    private String state;

    private Integer code;

    public SysException(String state, Integer code) {
        this.state = state;
        this.code = code;
    }

    public SysException(String state, Integer code, String message) {
        super(message);
        this.state = state;
        this.code = code;
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
}
