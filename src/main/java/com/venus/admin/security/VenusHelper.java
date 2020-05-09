package com.venus.admin.security;

import com.venus.admin.utils.BeanConvertUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.Map;

/**
 * 认证信息辅助
 * @Author: tcg
 * @Date: 2020/5/6 16:58
 * @Version 1.0
 */

public class VenusHelper {

    public static VenusUserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            OAuth2Request clientToken = oAuth2Authentication.getOAuth2Request();
            // 如果不是为客户端模式则返回全部信息
            if (!oAuth2Authentication.isClientOnly()) {
                if (authentication.getPrincipal() instanceof VenusUserDetails) {
                    return (VenusUserDetails) authentication.getPrincipal();
                }
                if (authentication.getPrincipal() instanceof Map) {
                    return BeanConvertUtils.mapToObject((Map) authentication.getPrincipal(), VenusUserDetails.class);
                }
                return (VenusUserDetails) authentication.getPrincipal();
            } else {
                // 客户端模式只返回客户端信息
                VenusUserDetails venusUser = new VenusUserDetails();
                venusUser.setClientId(clientToken.getClientId());
                venusUser.setAuthorities(clientToken.getAuthorities());
                return venusUser;
            }

        }
        return null;
    }

}
