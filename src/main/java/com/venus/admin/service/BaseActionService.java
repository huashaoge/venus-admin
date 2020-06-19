package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.model.entity.BaseAction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:27
 * @Version 1.0
 */

public interface BaseActionService extends IService<BaseAction> {

    /**
     * 移除相关按钮功能
     * @param menuId
     */
    void removeByMenuId(Long menuId);

    /**
     * 移除Action
     * @param actionId
     */
    void removeAction(Long actionId);

    /**
     * 获取功能
     * @param actionId
     * @return
     */
    BaseAction getAction(Long actionId);

    /**
     * 查询菜单下所有操作
     * @param menuId
     * @return
     */
    List<BaseAction> findListByMenuId(Long menuId);

    /**
     * 添加功能
     * @param baseAction
     * @return
     */
    BaseAction addAction(BaseAction baseAction);

    /**
     * 判断actionCode是否存在
     * @param actionCode
     * @return
     */
    boolean isExist(String actionCode);

    /**
     * 修改功能
     * @param action
     */
    BaseAction updateAction(BaseAction action);
}
