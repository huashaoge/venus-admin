package com.venus.admin.common.constants;

/**
 * @Author: tcg
 * @Date: 2020/4/22 14:51
 * @Version 1.0
 * 自定义返回码
 */

public enum ErrorCode {
    OK(20000,"OK"),
    /**
     * 系统内部错误
     */
    ERROR(50000,"ERROR"),
    ALERT(50001,"ALERT"),
    /**
     * 非500对应，自定义错误码700开始
     */
    FAIL(70000,"FAIL"),

    UNAUTHORIZED(40100,"UNAUTHORIZED")


    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCode getErrorCode(int code){
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ERROR;
    }


}
