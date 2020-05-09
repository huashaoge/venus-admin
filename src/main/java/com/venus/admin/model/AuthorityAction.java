package com.venus.admin.model;

import com.venus.admin.model.entity.BaseAction;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Objects;

/**
 * 功能权限
 * @Author: tcg
 * @Date: 2020/5/6 15:02
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorityAction extends BaseAction {

    private static final long serialVersionUID = -1775447038206470100L;

    /**
     * 权限ID
     */
    private Long authorityId;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 是否需要安全认证
     */
    private Boolean isAuth = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthorityAction)) {
            return false;
        }
        AuthorityAction a = (AuthorityAction) o;
        return this.authorityId.equals(a.getAuthorityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId);
    }
}
