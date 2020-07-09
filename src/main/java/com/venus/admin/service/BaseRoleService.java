package com.venus.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.common.model.PageParams;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.model.entity.BaseRoleUser;

import java.util.List;

/**
 * 角色管理
 * @Author: tcg
 * @Date: 2020/5/11 11:00
 * @Version 1.0
 */
public interface BaseRoleService extends IService<BaseRole> {
    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    List<BaseRole> getUserRoles(Long userId);

    /**
     * 分页查询
     * @param pageParams
     * @return
     */
    IPage<BaseRole> findListPage(PageParams pageParams);

    /**
     * 添加角色
     * @param role
     * @return
     */
    BaseRole addRole(BaseRole role);

    /**
     * 检查编码是否存在
     * @param roleCode
     * @return
     */
    boolean isExist(String roleCode);

    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    BaseRole getRole(Long roleId);

    /**
     * 更新角色
     * @param role
     * @return
     */
    BaseRole updateRole(BaseRole role);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(Long roleId);

    /**
     * 获取角色所有授权组员数量
     * @param roleId
     * @return
     */
    int getCountByRole(Long roleId);

    /**
     * 查询角色成员
     * @param roleId
     * @return
     */
    List<BaseRoleUser> findRoleUsers(Long roleId);

    /**
     * 移除角色组员
     * @param roleId
     */
    void removeRoleUsers(Long roleId);

    /**
     * 角色添加成员
     * @param roleId
     * @param userIds
     */
    void saveRoleUsers(Long roleId, String... userIds);

    /**
     * 查询列表
     * @return
     */
    List<BaseRole> findAllList();

    /**
     * 根据用户ID删除角色关联
     * @param userId
     */
    void removeUserRoles(Long userId);

    /**
     * 用户添加角色
     * @param userId
     * @param roles
     */
    void saveUserRoles(Long userId, String... roles);

    /**
     * 获取用户角色列表
     * @param userId
     * @return
     */
    List<Long> getUserRoleIds(Long userId);
}
