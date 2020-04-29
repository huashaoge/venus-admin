package com.venus.admin.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * @Author: tcg
 * @Date: 2020/4/29 11:11
 * @Version 1.0
 * 存储权限菜单标识
 */
public class VenusAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 4525771793834110344L;

    /**
     * 权限ID
     */
    private String authorityId;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 过期时间，判断权限是否已过期
     */
    private Date expireTime;

    /**
     * 权限所有者
     */
    private String owner;

    @JsonProperty("isExpired")
    public Boolean isExpired() {
        if (expireTime != null && System.currentTimeMillis() > expireTime.getTime()) {
            return true;
        }
        return false;
    }

    public VenusAuthority() {
    }

    public VenusAuthority(String authority) {
        this.authority = authority;
    }

    public VenusAuthority(String authority, Date expireTime) {
        this.authority = authority;
        this.expireTime = expireTime;
    }

    public VenusAuthority(String authorityId, String authority, Date expireTime, String owner) {
        this.authorityId = authorityId;
        this.authority = authority;
        this.expireTime = expireTime;
        this.owner = owner;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof VenusAuthority ? this.authority.equals(((VenusAuthority)obj).authority) : false;
        }
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
