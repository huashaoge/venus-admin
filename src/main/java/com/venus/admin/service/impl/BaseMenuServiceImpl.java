package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.common.constants.ResourceType;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseMenuMapper;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.service.BaseActionService;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:33
 * @Version 1.0
 */
@Service
public class BaseMenuServiceImpl extends ServiceImpl<BaseMenuMapper, BaseMenu> implements BaseMenuService {

    private final static int ENABLED = 1;

    @Autowired
    private BaseAuthorityService baseAuthorityService;

    @Autowired
    private BaseActionService baseActionService;

    @Override
    public void deleteMenu(Long menuId) {
        BaseMenu menu = this.getById(menuId);
        if (menu != null && menu.getIsPersist().equals(ENABLED)) {
            throw new VenusAlertException(String.format("保留数据，不允许删除!"));
        }
        // 移除菜单权限
        baseAuthorityService.removeAuthority(menuId, ResourceType.MENU);
        // 移除功能按钮和相关权限
        baseActionService.removeByMenuId(menuId);
        // 移除菜单信息
        this.removeById(menuId);
    }
}
