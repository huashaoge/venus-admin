package com.venus.admin.model;

import com.venus.admin.model.entity.BaseMenu;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

/**
 * 菜单权限
 * @Author: tcg
 * @Date: 2020/5/6 14:58
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorityMenu extends BaseMenu {

    private static final long serialVersionUID = -5511167274072912979L;

    /**
     * 权限ID
     */
    private Long authorityId;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 功能权限列表
     */
    private List<AuthorityAction> actionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o instanceof AuthorityMenu)) {
            return false;
        }
        AuthorityMenu a = (AuthorityMenu) o;
        return this.authorityId.equals(a.getAuthorityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId);
    }
}
