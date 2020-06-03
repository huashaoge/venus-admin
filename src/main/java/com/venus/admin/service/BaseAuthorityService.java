package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.common.constants.ResourceType;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthority;
import com.venus.admin.security.VenusAuthority;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:24
 * @Version 1.0
 */

public interface BaseAuthorityService extends IService<BaseAuthority> {
    /**
     * 根据用户获取权限目录列表
     * @param userId
     * @param root
     * @return
     */
    List<AuthorityMenu> findAuthorityMenuByUser(Long userId, Boolean root);

    /**
     * 获取菜单权限列表
     * @param status
     * @return
     */
    List<AuthorityMenu> findAuthorityMenu(Integer status);

    /**
     *  获取用户已授权权限
     * @param userId
     * @param root
     * @return
     */
    List<VenusAuthority> findAuthorityByUser(Long userId, Boolean root);

    /**
     * 获取所有可用权限
     * @param type
     * @return
     */
    List<VenusAuthority> findAuthorityByType(String type);

    /**
     * 获取角色已授权权限
     * @param roleId
     * @return
     */
    List<VenusAuthority> findAuthorityByRole(Long roleId);

    /**
     * 移除权限
     * @param resourceId
     * @param resourceType
     */
    void removeAuthority(Long resourceId, ResourceType resourceType);

    /**
     * 获取权限
     * @param resourceId
     * @param resourceType
     * @return
     */
    BaseAuthority getAuthority(Long resourceId, ResourceType resourceType);

    /**
     * 移除功能按钮权限
     * @param actionId
     */
    void removeAuthorityAction(Long actionId);

    /**
     * 保存或修改权限
     * @param resourceId
     * @param resourceType
     * @return
     */
    BaseAuthority saveOrUpdateAuthority(Long resourceId, ResourceType resourceType);
}
