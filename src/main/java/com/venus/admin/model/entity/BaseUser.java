package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: tcg
 * @Date: 2020/4/29 10:13
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@TableName("base_user")
public class BaseUser extends AbstractEntity{
    private static final long serialVersionUID = 2261974296021917378L;

    /**
     * 用户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 状态: 0-禁用 1-正常 2-锁定
     */
    private Integer status;
}
