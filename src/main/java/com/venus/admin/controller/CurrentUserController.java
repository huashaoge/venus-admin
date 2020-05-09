package com.venus.admin.controller;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.security.VenusHelper;
import com.venus.admin.security.VenusUserDetails;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 登录用户信息控制
 * @Author: tcg
 * @Date: 2020/5/6 15:48
 * @Version 1.0
 */
@RestController
public class CurrentUserController {

    @Autowired
    private BaseAuthorityService baseAuthorityService;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/current/user")
    public ResultBody getUserProfile() {
        return ResultBody.success().data(VenusHelper.getUser());
    }

    @GetMapping("/current/user/menus")
    public ResultBody<List<AuthorityMenu>> findAuthorityMenu() {
        // false 非admin 管理账户 管理账户直接授权
        List<AuthorityMenu> result = baseAuthorityService.findAuthorityMenuByUser(VenusHelper.getUser().getUserId(),true);
        return ResultBody.success().data(result);
    }

}
