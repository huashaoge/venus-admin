package com.venus.admin.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: tcg
 * @Date: 2020/6/23 14:46
 * @Version 1.0
 */
@Data
public class BaseRoleVO implements Serializable {

    private static final long serialVersionUID = 8422462461167028187L;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 状态: 0-无效 1-有效
     */
    private Integer status;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;
}
