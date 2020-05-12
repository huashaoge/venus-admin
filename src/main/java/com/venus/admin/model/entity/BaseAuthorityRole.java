package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色权限关联
 * @Author: tcg
 * @Date: 2020/5/11 11:42
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_authority_role")
public class BaseAuthorityRole extends AbstractEntity {

    private static final long serialVersionUID = 3208042960648695713L;

    /**
     * 权限ID
     */
    private Long authorityId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 过期时间
     */
    private Date expireTime;

}
