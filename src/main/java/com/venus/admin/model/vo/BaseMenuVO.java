package com.venus.admin.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tcg
 * @Date: 2020/5/20 17:23
 * @Version 1.0
 */
@Data
public class BaseMenuVO implements Serializable {

    private static final long serialVersionUID = -2715089835184077700L;
    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单编码
     */
    @NotBlank
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
    @NotNull(message = "优先级不能为空")
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

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 更新时间
     */
    public Date updateTime;

}
