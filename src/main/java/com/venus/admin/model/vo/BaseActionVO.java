package com.venus.admin.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: tcg
 * @Date: 2020/6/16 15:24
 * @Version 1.0
 */
@Data
public class BaseActionVO implements Serializable {


    private static final long serialVersionUID = -5084689901591793587L;
    /**
     * 资源ID
     */
    private Long actionId;

    /**
     * 资源编码
     */
    @NotBlank
    private String actionCode;

    /**
     * 资源名称
     */
    private String actionName;

    /**
     * 资源父节点
     */
    private Long menuId;

    /**
     * 优先级 越小越靠前
     */
    private Integer priority;

    /**
     * 资源描述
     */
    private String actionDesc;

    /**
     * 状态: 0-无效 1-有效
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
