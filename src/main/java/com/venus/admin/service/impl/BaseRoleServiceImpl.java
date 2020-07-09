package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.common.constants.BaseConstants;
import com.venus.admin.common.model.PageParams;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseRoleMapper;
import com.venus.admin.mapper.BaseRoleUserMapper;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.model.entity.BaseRoleUser;
import com.venus.admin.model.entity.BaseUser;
import com.venus.admin.service.BaseRoleService;
import com.venus.admin.service.BaseUserService;
import com.venus.admin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private BaseUserService baseUserService;

    public final static String ROOT = "admin";

    @Override
    public List<BaseRole> getUserRoles(Long userId) {
        return baseRoleUserMapper.selectRoleUserList(userId);
    }

    @Override
    public IPage<BaseRole> findListPage(PageParams pageParams) {
        BaseRole query = pageParams.mapToObject(BaseRole.class);
        QueryWrapper<BaseRole> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .likeRight(ObjectUtils.isNotEmpty(query.getRoleCode()), BaseRole::getRoleCode, query.getRoleCode())
                .likeRight(ObjectUtils.isNotEmpty(query.getRoleName()), BaseRole::getRoleName, query.getRoleName());
        queryWrapper.orderByDesc("create_time");
        return baseRoleMapper.selectPage(pageParams, queryWrapper);
    }

    @Override
    public BaseRole addRole(BaseRole role) {
        if (isExist(role.getRoleCode())) {
            throw new VenusAlertException(String.format("%编码已存在！", role.getRoleCode()));
        }
        if (role.getStatus() == null) {
            role.setStatus(BaseConstants.ENABLED);
        }
        if (role.getIsPersist() == null ) {
            role.setIsPersist(BaseConstants.DISABLED);
        }
        role.setCreateTime(new Date());
        role.setUpdateTime(role.getCreateTime());
        baseRoleMapper.insert(role);
        return role;
    }

    @Override
    public boolean isExist(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new VenusAlertException("roleCode不能为空");
        }
        QueryWrapper<BaseRole> query = new QueryWrapper();
        query.lambda().eq(BaseRole::getRoleCode, roleCode);
        return baseRoleMapper.selectCount(query) > 0;
    }

    @Override
    public BaseRole getRole(Long roleId){
        return baseRoleMapper.selectById(roleId);
    }

    @Override
    public BaseRole updateRole(BaseRole role) {
        BaseRole saved = getRole(role.getRoleId());
        if (role == null) {
            throw new VenusAlertException("信息不存在");
        }
        if (!saved.getRoleCode().equals(role.getRoleCode())) {
            // 重新检查唯一性
            if (isExist(role.getRoleCode())) {
                throw new VenusAlertException(String.format("%编码已存在!", role.getRoleCode()));
            }
        }
        role.setUpdateTime(new Date());
        baseRoleMapper.updateById(role);
        return role;
    }

    @Override
    public void deleteRole(Long roleId) {
        if (roleId == null) {
            return;
        }
        BaseRole role = getRole(roleId);
        if (role != null && role.getIsPersist().equals(BaseConstants.ENABLED)) {
            throw new VenusAlertException(String.format("保留数据，不允许删除"));
        }
        int count = getCountByRole(roleId);
        if (count > 0) {
            throw new VenusAlertException("该角色下存在授权人员，不允许删除!");
        }
        baseRoleMapper.deleteById(roleId);
    }

    @Override
    public int getCountByRole(Long roleId) {
        QueryWrapper<BaseRoleUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseRoleUser::getRoleId, roleId);
        int result = baseRoleUserMapper.selectCount(queryWrapper);
        return result;
    }

    @Override
    public List<BaseRoleUser> findRoleUsers(Long roleId) {
        QueryWrapper<BaseRoleUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseRoleUser::getRoleId, roleId);
        return baseRoleUserMapper.selectList(queryWrapper);
    }

    @Override
    public void removeRoleUsers(Long roleId) {
        QueryWrapper<BaseRoleUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseRoleUser::getRoleId, roleId);
        baseRoleUserMapper.delete(queryWrapper);
    }

    @Override
    public void saveRoleUsers(Long roleId, String... userIds) {
        if (roleId == null || userIds == null) {
            return;
        }
        // 先清空，再添加
        removeRoleUsers(roleId);
        if (userIds.length > 0) {
            for (String userId : userIds) {
                BaseRoleUser roleUser = new BaseRoleUser();
                roleUser.setUserId(Long.parseLong(userId));
                roleUser.setRoleId(roleId);
                baseRoleUserMapper.insert(roleUser);
            }
        }
    }

    @Override
    public List<BaseRole> findAllList() {
        List<BaseRole> list = baseRoleMapper.selectList(new QueryWrapper<>());
        return list;
    }

    @Override
    public void removeUserRoles(Long userId) {
        QueryWrapper<BaseRoleUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseRoleUser::getUserId, userId);
        baseRoleUserMapper.delete(queryWrapper);
    }

    @Override
    public void saveUserRoles(Long userId, String... roles) {
        if (userId == null || roles == null) {
            return;
        }
        BaseUser user = baseUserService.getUserById(userId);
        if (user == null) {
            return;
        }
        if (ROOT.equals(user.getUserName())) {
            throw new VenusAlertException("默认用户无需分配!");
        }
        // 清空再添加
        removeUserRoles(userId);
        if (roles.length > 0) {
            // 可优化成批量插入
            for (String roleId : roles) {
                BaseRoleUser roleUser = new BaseRoleUser();
                roleUser.setUserId(userId);
                roleUser.setRoleId(Long.parseLong(roleId));
                baseRoleUserMapper.insert(roleUser);
            }
        }
    }

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return baseRoleUserMapper.selectRoleUserIdList(userId);
    }
}
