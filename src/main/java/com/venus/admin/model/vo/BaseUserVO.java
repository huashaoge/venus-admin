package com.venus.admin.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: tcg
 * @Date: 2020/7/7 15:31
 * @Version 1.0
 */
@Data
public class BaseUserVO implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户类型: super-超级管理员 normal-普通管理员
     */
    private String userType;

    /**
     * 描述
     */
    private String userDesc;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态: 0-禁用 1-正常 2-锁定
     */
    private Integer status;
}
