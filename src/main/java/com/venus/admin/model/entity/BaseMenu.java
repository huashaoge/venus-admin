package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: tcg
 * @Date: 2020/5/6 14:27
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_menu")
public class BaseMenu extends AbstractEntity{

    private static final long serialVersionUID = -3643534932218937353L;

    /**
     * 菜单ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long menuId;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父级菜单
     */
    private Long parentId;

    /**
     * 请求协议://, http:// ,https://
     */
    private String scheme;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 打开方式: _self窗口内，_blank新窗口
     */
    private String target;

    /**
     * 优先级 越小越靠前
     */
    private Integer priority;

    /**
     * 描述
     */
    private String menuDesc;

    /**
     * 状态：0-无效 1-有效
     */
    private Integer status;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;

    /**
     * 服务ID
     */
    private String serviceId;

}
