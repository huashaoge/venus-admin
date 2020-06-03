package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.common.constants.ResourceType;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseMenuMapper;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.service.BaseActionService;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Value("${spring.application.name}")
    private String DEFAULT_SERVICE_ID;

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

    /**
     * 检查菜单编码是否存在
     * @param menuCode
     * @return
     */
    @Override
    public Boolean isExist(String menuCode) {
        QueryWrapper<BaseMenu> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseMenu::getMenuCode, menuCode);
        int count = this.count(queryWrapper);
        return count > 0 ? true : false;
    }


    /**
     * 添加菜单资源
     * @param baseMenu
     * @return
     */
    @Override
    public BaseMenu addMenu(BaseMenu baseMenu) {
        if (isExist(baseMenu.getMenuCode())) {
            throw new VenusAlertException(String.format("%s编码已存在!", baseMenu.getMenuCode()));
        }
        if (baseMenu.getParentId() == null) {
            baseMenu.setParentId(0L);
        }
        if (baseMenu.getPriority() == null) {
            baseMenu.setPriority(0);
        }
        if (baseMenu.getStatus() == null) {
            baseMenu.setStatus(1);
        }
        if (baseMenu.getIsPersist() == null) {
            baseMenu.setIsPersist(0);
        }
        baseMenu.setServiceId(DEFAULT_SERVICE_ID);
        baseMenu.setCreateTime(new Date());
        baseMenu.setUpdateTime(baseMenu.getCreateTime());
        this.save(baseMenu);
        // 同步权限表里的信息
        baseAuthorityService.saveOrUpdateAuthority(baseMenu.getMenuId(),ResourceType.MENU);
        return null;
    }


}
