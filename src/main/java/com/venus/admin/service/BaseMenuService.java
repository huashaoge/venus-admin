package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.model.entity.BaseMenu;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:33
 * @Version 1.0
 */

public interface BaseMenuService extends IService<BaseMenu> {
    /**
     * 移除菜单
     * @param menuId
     */
    void deleteMenu(Long menuId);
}
