package com.venus.admin.exception;

/**
 * @Author: tcg
 * @Date: 2020/5/18 15:56
 * @Version 1.0
 */
public class VenusAlertException extends VenusException{

    private static final long serialVersionUID = 3791547344280032592L;

    public VenusAlertException() {}

    public VenusAlertException(String msg) {
        super(msg);
    }

    public VenusAlertException(int code, String msg) {super(code, msg);}

    public VenusAlertException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

}
