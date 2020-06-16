package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.common.constants.BaseConstants;
import com.venus.admin.common.constants.ResourceType;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseActionMapper;
import com.venus.admin.model.entity.BaseAction;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.service.BaseActionService;
import com.venus.admin.service.BaseAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:37
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseActionServiceImpl extends ServiceImpl<BaseActionMapper, BaseAction> implements BaseActionService {

    @Value("${spring.application.name}")
    private String DEFAULT_SERVICE_ID;

    @Autowired
    private BaseActionMapper baseActionMapper;

    @Autowired
    private BaseAuthorityService baseAuthorityService;


    @Override
    public void removeByMenuId(Long menuId) {
        List<BaseAction> actionList = findListByMenuId(menuId);
        if (actionList != null && actionList.size() > 0) {
            for (BaseAction baseAction : actionList) {
                removeAction(baseAction.getActionId());
            }
        }
    }

    @Override
    public void removeAction(Long actionId) {
        BaseAction action = getAction(actionId);
        if (action != null && action.getIsPersist().equals(BaseConstants.ENABLED)) {
            throw new VenusAlertException(String.format("保留数据，不允许删除"));
        }
        baseAuthorityService.removeAuthorityAction(actionId);
        baseAuthorityService.removeAuthority(actionId, ResourceType.ACTION);
        baseActionMapper.deleteById(actionId);
    }

    @Override
    public BaseAction getAction(Long actionId) {
        return baseActionMapper.selectById(actionId);
    }

    @Override
    public List<BaseAction> findListByMenuId(Long menuId) {
        QueryWrapper<BaseAction> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseAction::getMenuId, menuId);
        List<BaseAction> list = baseActionMapper.selectList(queryWrapper);
        list.sort(Comparator.comparing(BaseAction::getPriority));
        return list;
    }

    /**
     * 添加功能
     * @param baseAction
     * @return
     */
    @Override
    public BaseAction addAction(BaseAction baseAction) {
        if(isExist(baseAction.getActionCode())){
            throw new VenusAlertException(String.format("%编码已存在",baseAction.getActionCode()));
        }
        if (baseAction.getMenuId() == null) {
            baseAction.setMenuId(0L);
        }
        if (baseAction.getPriority() == null) {
            baseAction.setPriority(0);
        }
        if (baseAction.getStatus() == null) {
            baseAction.setStatus(BaseConstants.ENABLED);
        }
        if (baseAction.getIsPersist() == null) {
            baseAction.setIsPersist(BaseConstants.DISABLED);
        }
        baseAction.setCreateTime(new Date());
        baseAction.setServiceId(DEFAULT_SERVICE_ID);
        baseAction.setUpdateTime(baseAction.getCreateTime());
        baseActionMapper.insert(baseAction);
        // 同步添加权限表里的信息
        baseAuthorityService.saveOrUpdateAuthority(baseAction.getActionId(), ResourceType.ACTION);
        return baseAction;
    }

    @Override
    public boolean isExist(String actionCode) {
        QueryWrapper<BaseAction> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(BaseAction::getActionCode,actionCode);
        int count = baseActionMapper.selectCount(queryWrapper);
        return count > 0 ? true : false;
    }
}
