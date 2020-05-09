package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:02
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@TableName("base_authority")
public class BaseAuthority extends AbstractEntity {

    private static final long serialVersionUID = 7147999936080661941L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long authorityId;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 菜单资源ID
     */
    private Long menuId;

    /**
     * API资源ID
     */
    private Long apiId;

    /**
     * 操作资源ID
     */
    private Long actionId;

    /**
     * 状态
     */
    private Integer status;
}
