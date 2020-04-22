package com.venus.admin.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.venus.admin.common.constants.ErrorCode;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/4/22 13:47
 * @Version 1.0
 * 通用结果返回
 */

public class ResultBody<T> implements Serializable {

    private static final long serialVersionUID = 7387675511620897834L;

    /**
     * 响应编码 一般与http状态对应 后面补00
     */
    private int code = 20000;

    /**
     * 响应提示消息
     */
    private String message;


    /**
     * 响应数据
     */
    private T data;

    /**
     * http 状态码
     */
    private int httpStatus;

    /**
     * 额外需要附加的信息，非data区域返回
     */
    private Map<String,Object> extra;

    /**
     * 响应时间
     */
    private long timestamp = System.currentTimeMillis();

    public ResultBody() {
        super();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @JsonIgnore
    public int getHttpStatus() {
        return httpStatus;
    }

    public ResultBody code(int code) {
        this.code = code;
        return this;
    }

    public ResultBody msg(String message) {
        this.message = message;
        return this;
    }

    public ResultBody data(T data) {
        this.data = data;
        return this;
    }

    public ResultBody httpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ResultBody put(String key, Object value) {
        if (this.extra == null) {
            this.extra = Maps.newHashMap();
        }
        this.extra.put(key, value);
        return this;
    }

    /**
     * 默认成功返回，20000，成功
     * @return
     */
    public static ResultBody success() {
        return new ResultBody().code(ErrorCode.OK.getCode()).msg(ErrorCode.OK.getMessage());
    }

    /**
     * 返回失败 50000
     * @return
     */
    public static ResultBody fail() {
        return new ResultBody().code(ErrorCode.FAIL.getCode()).msg(ErrorCode.FAIL.getMessage());
    }

    @Override
    public String toString() {
        return "ResultBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", httpStatus=" + httpStatus +
                ", extra=" + extra +
                ", timestamp=" + timestamp +
                '}';
    }
}
