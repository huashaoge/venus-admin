package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.venus.admin.mapper.BaseUserMapper;
import com.venus.admin.model.UserAccount;
import com.venus.admin.model.entity.BaseAccount;
import com.venus.admin.model.entity.BaseUser;
import com.venus.admin.security.VenusAuthority;
import com.venus.admin.service.BaseAccountService;
import com.venus.admin.service.BaseUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/4/29 10:37
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    @Autowired
    private BaseAccountService baseAccountService;

    @Override
    public UserAccount login(String account) {
        if (StringUtils.isEmpty(account)) {
            return null;
        }
        // 获取BaseAccount 基础账户数据 默认用户名 扩展时修改
        BaseAccount baseAccount = baseAccountService.getAccount(account,"username");
        UserAccount userAccount = getUserAccount(baseAccount.getUserId());
        BeanUtils.copyProperties(baseAccount,userAccount);
        return userAccount;
    }

    @Override
    public UserAccount getUserAccount(Long userId) {
        // 用户权限列表
        List<VenusAuthority> authorities = Lists.newArrayList();
        // 用户角色列表
        List<Map> roles = Lists.newArrayList();
        // TODO 数据库查询角色
        // BaseUserRole 表查询 用户角色 循环插入角色详情与标志
        Map roleMap = Maps.newHashMap();
        roleMap.put("roleId", "1");
        roleMap.put("roleCode","admin");
        roleMap.put("roleName", "系统管理员");
        // 角色详情
        roles.add(roleMap);
        // 加入角色标志
        VenusAuthority authorityRole = new VenusAuthority("1","ROLE_admin",null,"role");
        authorities.add(authorityRole);


        BaseUser baseUser = this.getById(userId);
        // 加入用户权限
        VenusAuthority authorityUser = new VenusAuthority("1","MENU_system",null,"role");
        authorities.add(authorityUser);


        UserAccount userAccount = new UserAccount();
        userAccount.setNickName(baseUser.getNickName());
        userAccount.setAvatar(baseUser.getAvatar());
        userAccount.setAuthorities(authorities);
        userAccount.setRoles(roles);
        return userAccount;
    }


}
