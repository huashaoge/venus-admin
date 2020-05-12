package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.model.entity.BaseRole;

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
}
