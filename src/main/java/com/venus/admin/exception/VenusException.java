package com.venus.admin.exception;

import com.venus.admin.common.constants.ErrorCode;

/**
 * 基础错误异常
 * @Author: tcg
 * @Date: 2020/5/15 11:26
 * @Version 1.0
 */
public class VenusException extends RuntimeException{

    private static final long serialVersionUID = 4596108518173891446L;

    private int code = ErrorCode.ERROR.getCode();

    public VenusException() {

    }

    public VenusException(String msg) {
        super(msg);
    }

    public VenusException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public VenusException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
