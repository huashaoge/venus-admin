package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: tcg
 * @Date: 2020/4/29 10:50
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_account")
public class BaseAccount extends AbstractEntity{

    private static final long serialVersionUID = 2597086279505206866L;

    /**
     * 登录账号ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long accountId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 唯一标识，用户名、手机号、邮箱、或者第三方应用的唯一标识
     */
    private String account;

    /**
     * 密码凭证
     */
    private String password;

    /**
     * 登录类型 username/mobile/email/weixin...
     */
    private String accountType;

    /**
     * 注册IP
     */
    private String registerIp;

    /**
     * 状态 0-禁用 1-启用 2-锁定
     */
    private Integer status;


    /**
     * 账号域
     */
    private String domain;

    public BaseAccount(Long userId, String account, String password, String accountType, String domain, String registerIp) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.accountType = accountType;
        this.domain = domain;
        this.registerIp = registerIp;
    }
}
