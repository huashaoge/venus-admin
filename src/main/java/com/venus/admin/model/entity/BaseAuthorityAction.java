package com.venus.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: tcg
 * @Date: 2020/5/18 17:31
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_authority_action")
public class BaseAuthorityAction extends AbstractEntity{

    private static final long serialVersionUID = 2645143312372055071L;

    /**
     * 操作资源ID
     */
    private Long actionId;

    /**
     * 权限ID
     */
    private Long authorityId;

}
