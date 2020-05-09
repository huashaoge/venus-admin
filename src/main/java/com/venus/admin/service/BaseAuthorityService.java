package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthority;
import org.springframework.stereotype.Service;

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
    List<AuthorityMenu> findAuthorityMenuByUser(Long userId, boolean root);

    /**
     * 获取菜单权限列表
     * @param status
     * @return
     */
    List<AuthorityMenu> findAuthorityMenu(Integer status);
}
