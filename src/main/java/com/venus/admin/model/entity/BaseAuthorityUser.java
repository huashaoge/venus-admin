package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统权限-用户关联
 * @Author: tcg
 * @Date: 2020/5/11 15:26
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_authority_user")
public class BaseAuthorityUser extends AbstractEntity{

    private static final long serialVersionUID = -3207908257020188933L;

    /**
     * 权限ID
     */
    private Long authorityId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 过期时间
     */
    private Date expireTime;
}
