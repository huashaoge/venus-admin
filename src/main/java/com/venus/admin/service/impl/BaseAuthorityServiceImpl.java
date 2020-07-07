package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.venus.admin.common.constants.BaseConstants;
import com.venus.admin.common.constants.ResourceType;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseAuthorityActionMapper;
import com.venus.admin.mapper.BaseAuthorityMapper;
import com.venus.admin.mapper.BaseAuthorityRoleMapper;
import com.venus.admin.mapper.BaseAuthorityUserMapper;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.*;
import com.venus.admin.security.VenusAuthority;
import com.venus.admin.service.BaseActionService;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.service.BaseMenuService;
import com.venus.admin.service.BaseRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:50
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseAuthorityServiceImpl extends ServiceImpl<BaseAuthorityMapper, BaseAuthority> implements BaseAuthorityService {

    @Autowired
    private BaseAuthorityMapper baseAuthorityMapper;

    @Autowired
    private BaseRoleService baseRoleService;

    @Autowired
    private BaseAuthorityRoleMapper baseAuthorityRoleMapper;

    @Autowired
    private BaseAuthorityUserMapper baseAuthorityUserMapper;

    @Autowired
    private BaseAuthorityActionMapper baseAuthorityActionMapper;

    @Autowired
    private BaseMenuService baseMenuService;

    @Autowired
    private BaseActionService baseActionService;

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
        if (CollectionUtils.isNotEmpty(rolesList)) {
            for (BaseRole role : rolesList) {
                // 角色授权目录
                List<AuthorityMenu> roleGrantedAuthorities = baseAuthorityRoleMapper.selectAuthorityMenuByRole(role.getRoleId());
                if (CollectionUtils.isNotEmpty(roleGrantedAuthorities)) {
                    authorities.addAll(roleGrantedAuthorities);
                }
            }
        }
        // 加入用户特殊授权
        List<AuthorityMenu> userGrantedAuthorities = baseAuthorityUserMapper.selectAuthorityMenuByUser(userId);
        if (CollectionUtils.isNotEmpty(userGrantedAuthorities)) {
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
        if (CollectionUtils.isNotEmpty(rolesList)) {
            for (BaseRole role : rolesList) {
                // 加入角色已授权
                List<VenusAuthority> roleGrantedAuthorities = findAuthorityByRole(role.getRoleId());
                if (CollectionUtils.isNotEmpty(roleGrantedAuthorities)) {
                    authorities.addAll(roleGrantedAuthorities);
                }
            }
        }
        // 用户特殊权限
        List<VenusAuthority> userGrantedAuthorities = baseAuthorityUserMapper.selectAuthorityByUser(userId);
        if (CollectionUtils.isNotEmpty(userGrantedAuthorities)) {
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

    @Override
    public void removeAuthority(Long resourceId, ResourceType resourceType) {
        if (isGranted(resourceId, resourceType)) {
            throw new VenusAlertException(String.format("资源已被授权，不允许删除！取消授权后，再次尝试！"));
        }
        QueryWrapper<BaseAuthority> queryWrapper = buildQueryWrapper(resourceId, resourceType);
        baseAuthorityMapper.delete(queryWrapper);
    }

    /**
     * 构建权限对象
     * @param resourceId
     * @param resourceType
     * @return
     */
    private QueryWrapper<BaseAuthority> buildQueryWrapper(Long resourceId, ResourceType resourceType) {
        QueryWrapper<BaseAuthority> queryWrapper = new QueryWrapper();
        if (ResourceType.MENU.equals(resourceType)) {
            queryWrapper.lambda().eq(BaseAuthority::getMenuId, resourceId);
        }
        if (ResourceType.ACTION.equals(resourceType)) {
            queryWrapper.lambda().eq(BaseAuthority::getActionId, resourceId);
        }
        return queryWrapper;
    }

    /**
     * 是否已被授权
     * @param resourceId
     * @param resourceType
     * @return
     */
    public Boolean isGranted(Long resourceId, ResourceType resourceType) {
        BaseAuthority authority = getAuthority(resourceId, resourceType);
        if (authority == null || authority.getAuthorityId() == null) {
            return false;
        }

        QueryWrapper<BaseAuthorityRole> roleQueryWrapper = new QueryWrapper();
        roleQueryWrapper.lambda().eq(BaseAuthorityRole::getAuthorityId, authority.getAuthorityId());
        int roleGrantedCount = baseAuthorityRoleMapper.selectCount(roleQueryWrapper);
        QueryWrapper<BaseAuthorityUser> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.lambda().eq(BaseAuthorityUser::getAuthorityId, authority.getAuthorityId());
        int userGrantedCount = baseAuthorityUserMapper.selectCount(userQueryWrapper);
        return roleGrantedCount > 0 || userGrantedCount > 0;
    }

    @Override
    public BaseAuthority getAuthority(Long resourceId, ResourceType resourceType) {
        if (resourceId == null || resourceType == null) {
            return null;
        }
        QueryWrapper<BaseAuthority> queryWrapper = buildQueryWrapper(resourceId, resourceType);
        return baseAuthorityMapper.selectOne(queryWrapper);
    }

    @Override
    public void removeAuthorityAction(Long actionId) {
        QueryWrapper<BaseAuthorityAction> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseAuthorityAction::getActionId, actionId);
        baseAuthorityActionMapper.delete(queryWrapper);
    }

    @Override
    public BaseAuthority saveOrUpdateAuthority(Long resourceId, ResourceType resourceType) {
        BaseAuthority baseAuthority = getAuthority(resourceId, resourceType);
        String authority = null;
        if (baseAuthority == null) {
            baseAuthority = new BaseAuthority();
        }
        if (ResourceType.MENU.equals(resourceType)) {
            BaseMenu menu = baseMenuService.getById(resourceId);
            authority = BaseConstants.AUTHORITY_PREFIX_MENU + menu.getMenuCode();
            baseAuthority.setMenuId(resourceId);
            baseAuthority.setStatus(menu.getStatus());
        }

        if (ResourceType.ACTION.equals(resourceType)) {
            BaseAction baseAction = baseActionService.getAction(resourceId);
            authority = BaseConstants.AUTHORITY_PREFIX_ACTION + baseAction.getActionCode();
            baseAuthority.setActionId(resourceId);
            baseAuthority.setStatus(baseAction.getStatus());
        }

        if (authority == null) {
            return null;
        }
        // 设置权限标识
        baseAuthority.setAuthority(authority);
        if (baseAuthority.getAuthorityId() == null) {
            baseAuthority.setCreateTime(new Date());
            baseAuthority.setUpdateTime(baseAuthority.getCreateTime());
            // 新增权限
            this.save(baseAuthority);
        } else {
            baseAuthority.setUpdateTime(new Date());
            this.updateById(baseAuthority);
        }
        return baseAuthority;
    }

    @Override
    public void addAuthorityRole(Long roleId, Date expireTime, String... authorityIds) {
        if (roleId == null) {
            return;
        }
        // 清空角色授权
        QueryWrapper<BaseAuthorityRole> roleQueryWrapper = new QueryWrapper();
        roleQueryWrapper.lambda().eq(BaseAuthorityRole::getRoleId, roleId);
        baseAuthorityRoleMapper.delete(roleQueryWrapper);
        BaseAuthorityRole authority = null;
        if (authorityIds != null && authorityIds.length > 0) {
            for (String id : authorityIds) {
                authority = new BaseAuthorityRole();
                authority.setAuthorityId(Long.parseLong(id));
                authority.setRoleId(roleId);
                authority.setExpireTime(expireTime);
                baseAuthorityRoleMapper.insert(authority);
            }
        }
    }

}
