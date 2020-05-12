package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.venus.admin.mapper.BaseAuthorityMapper;
import com.venus.admin.mapper.BaseAuthorityRoleMapper;
import com.venus.admin.mapper.BaseAuthorityUserMapper;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthority;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.security.VenusAuthority;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.service.BaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:50
 * @Version 1.0
 */
@Service
public class BaseAuthorityServiceImpl extends ServiceImpl<BaseAuthorityMapper, BaseAuthority> implements BaseAuthorityService {

    @Autowired
    private BaseAuthorityMapper baseAuthorityMapper;

    @Autowired
    private BaseRoleService baseRoleService;

    @Autowired
    private BaseAuthorityRoleMapper baseAuthorityRoleMapper;

    @Autowired
    private BaseAuthorityUserMapper baseAuthorityUserMapper;

    @Override
    public List<AuthorityMenu> findAuthorityMenuByUser(Long userId, Boolean root) {
        if (root) {
            // 超级管理员返回所有
            return findAuthorityMenu(null);
        }
        // 用户权限列表
        List<AuthorityMenu> authorities = Lists.newArrayList();
        // 角色列表
        List<BaseRole> rolesList = baseRoleService.getUserRoles(userId);
        if (rolesList != null && rolesList.size() > 0) {
            for (BaseRole role : rolesList) {
                // 角色授权目录
                List<AuthorityMenu> roleGrantedAuthorities = baseAuthorityRoleMapper.selectAuthorityMenuByRole(role.getRoleId());
                if (roleGrantedAuthorities != null && roleGrantedAuthorities.size() > 0) {
                    authorities.addAll(roleGrantedAuthorities);
                }
            }
        }
        // 加入用户特殊授权
        List<AuthorityMenu> userGrantedAuthorities = baseAuthorityUserMapper.selectAuthorityMenuByUser(userId);
        if (userGrantedAuthorities != null && userGrantedAuthorities.size() > 0) {
            authorities.addAll(userGrantedAuthorities);
        }
        // 权限去重
        authorities = authorities.stream().distinct().collect(Collectors.toList());
        authorities.sort(Comparator.comparing(BaseMenu::getPriority));
        return authorities;
    }

    @Override
    public List<AuthorityMenu> findAuthorityMenu(Integer status) {
        Map map = Maps.newHashMap();
        map.put("status", status);
        List<AuthorityMenu> authorities = baseAuthorityMapper.selectAuthorityMenu(map);
        authorities.sort(Comparator.comparing(BaseMenu::getPriority));
        return authorities;
    }

    @Override
    public List<VenusAuthority> findAuthorityByUser(Long userId, Boolean root) {
        if (root) {
            // 管理员返回所有
            return findAuthorityByType("1");
        }
        List<VenusAuthority> authorities = Lists.newArrayList();
        List<BaseRole> rolesList = baseRoleService.getUserRoles(userId);
        if (rolesList != null && rolesList.size() > 0) {
            for (BaseRole role : rolesList) {
                // 加入角色已授权
                List<VenusAuthority> roleGrantedAuthorities = findAuthorityByRole(role.getRoleId());
                if (roleGrantedAuthorities != null && roleGrantedAuthorities.size() > 0) {
                    authorities.addAll(roleGrantedAuthorities);
                }
            }
        }
        // 用户特殊权限
        List<VenusAuthority> userGrantedAuthorities = baseAuthorityUserMapper.selectAuthorityByUser(userId);
        if (userGrantedAuthorities != null && userGrantedAuthorities.size() > 0) {
            authorities.addAll(userGrantedAuthorities);
        }
        // 权限去重
        authorities = authorities.stream().distinct().collect(Collectors.toList());
        return authorities;
    }

    /**
     * 获取所有可用权限
     * @param type null 查询全部 1 获取菜单和操作 2 获取API
     * @return
     */
    @Override
    public List<VenusAuthority> findAuthorityByType(String type) {
        Map map = Maps.newHashMap();
        map.put("type", type);
        map.put("status", 1);
        return baseAuthorityMapper.selectAuthorityAll(map);
    }

    @Override
    public List<VenusAuthority> findAuthorityByRole(Long roleId) {
        return baseAuthorityRoleMapper.selectAuthorityByRole(roleId);
    }
}
