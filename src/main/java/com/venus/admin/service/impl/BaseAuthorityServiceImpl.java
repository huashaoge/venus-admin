package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.venus.admin.mapper.BaseAuthorityMapper;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthority;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.service.BaseAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:50
 * @Version 1.0
 */
@Service
public class BaseAuthorityServiceImpl extends ServiceImpl<BaseAuthorityMapper, BaseAuthority> implements BaseAuthorityService {

    @Autowired
    private BaseAuthorityMapper baseAuthorityMapper;

    @Override
    public List<AuthorityMenu> findAuthorityMenuByUser(Long userId, boolean root) {
        if (root) {
            // 超级管理员返回所有
            return findAuthorityMenu(null);
        }
        return null;
    }

    @Override
    public List<AuthorityMenu> findAuthorityMenu(Integer status) {
        Map map = Maps.newHashMap();
        map.put("status", status);
        List<AuthorityMenu> authorities = baseAuthorityMapper.selectAuthorityMenu(map);
        authorities.sort(Comparator.comparing(BaseMenu::getPriority));
        return authorities;
    }
}
