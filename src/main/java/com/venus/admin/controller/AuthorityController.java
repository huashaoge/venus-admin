package com.venus.admin.controller;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseUser;
import com.venus.admin.security.VenusAuthority;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.service.BaseUserService;
import com.venus.admin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/6/29 15:56
 * @Version 1.0
 */
@RestController
public class AuthorityController {

    @Autowired
    private BaseAuthorityService baseAuthorityService;

    @Autowired
    private BaseUserService baseUserService;

    public final static String ROOT = "admin";

    @GetMapping("/authority/menu")
    public ResultBody<List<AuthorityMenu>> findAuthorityMenu() {
        List<AuthorityMenu> result = baseAuthorityService.findAuthorityMenu(1);
        return ResultBody.success().data(result);
    }

    @GetMapping("/authority/role")
    public ResultBody<List<VenusAuthority>> findAuthorityRole(Long roleId) {
        List<VenusAuthority> result = baseAuthorityService.findAuthorityByRole(roleId);
        return ResultBody.success().data(result);
    }

    @GetMapping("/authority/user")
    public ResultBody<List<VenusAuthority>> findAuthorityUser(@RequestParam Long userId) {
        BaseUser user = baseUserService.getUserById(userId);
        List<VenusAuthority> result = baseAuthorityService.findAuthorityByUser(userId,ROOT.equals(user.getUserName()));
        return ResultBody.success().data(result);
    }

    @PostMapping("/authority/user/grant")
    public ResultBody grantAuthorityUser(
            @RequestParam Long userId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date expireTime,
            @RequestParam(required = false) String authorityIds
    ) {
        baseAuthorityService.addAuthorityUser(userId, expireTime, StringUtils.isNotBlank(authorityIds) ? authorityIds.split(",") : new String[]{});
        return ResultBody.success();
    }

    @PostMapping("/authority/role/grant")
    public ResultBody grantAuthorityRole(
            @RequestParam(value = "roleId") Long roleId,
            @RequestParam(value = "expireTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date expireTime,
            @RequestParam(value = "authorityIds", required = false) String authorityIds
    ) {
        baseAuthorityService.addAuthorityRole(roleId, expireTime, StringUtils.isNotBlank(authorityIds)? authorityIds.split(","):new String[]{});
        return ResultBody.success();
    }

}
