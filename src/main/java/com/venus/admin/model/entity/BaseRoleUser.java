package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: tcg
 * @Date: 2020/5/11 11:06
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_role_user")
public class BaseRoleUser extends AbstractEntity{

    private static final long serialVersionUID = 1120515582386384391L;

    /**
     * 系统用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
