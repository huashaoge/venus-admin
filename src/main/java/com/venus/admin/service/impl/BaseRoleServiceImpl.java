package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.mapper.BaseRoleMapper;
import com.venus.admin.mapper.BaseRoleUserMapper;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.service.BaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/11 11:01
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseRoleServiceImpl extends ServiceImpl<BaseRoleMapper, BaseRole> implements BaseRoleService {

    @Autowired
    private BaseRoleMapper baseRoleMapper;
    @Autowired
    private BaseRoleUserMapper baseRoleUserMapper;

    @Override
    public List<BaseRole> getUserRoles(Long userId) {
        return baseRoleUserMapper.selectRoleUserList(userId);
    }
}
