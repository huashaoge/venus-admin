package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统角色基础信息
 * @Author: tcg
 * @Date: 2020/5/11 10:44
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_role")
public class BaseRole extends AbstractEntity{

    private static final long serialVersionUID = 9214064616544984044L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.ASSIGN_ID)
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
