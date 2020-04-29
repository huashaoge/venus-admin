package com.venus.admin.model;

import com.google.common.collect.Lists;
import com.venus.admin.model.entity.BaseAccount;
import com.venus.admin.security.VenusAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 返回交互需要的所有账户信息
 * @Author: tcg
 * @Date: 2020/4/29 11:06
 * @Version 1.0
 */

public class UserAccount extends BaseAccount implements Serializable {

    private static final long serialVersionUID = -9186227432569445208L;

    /**
     * 用户角色
     */
    private Collection<Map> roles = Lists.newArrayList();

    /**
     * 用户权限
     */
    private Collection<VenusAuthority> authorities = Lists.newArrayList();

    private String nickName;

    private String avatar;

    public Collection<Map> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Map> roles) {
        this.roles = roles;
    }

    public Collection<VenusAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<VenusAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
