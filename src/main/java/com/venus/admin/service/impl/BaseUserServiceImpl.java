package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.venus.admin.common.constants.BaseConstants;
import com.venus.admin.common.model.PageParams;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseUserMapper;
import com.venus.admin.model.UserAccount;
import com.venus.admin.model.entity.BaseAccount;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.model.entity.BaseUser;
import com.venus.admin.security.VenusAuthority;
import com.venus.admin.service.BaseAccountService;
import com.venus.admin.service.BaseAuthorityService;
import com.venus.admin.service.BaseRoleService;
import com.venus.admin.service.BaseUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/4/29 10:37
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

    @Autowired
    private BaseAccountService baseAccountService;

    @Autowired
    private BaseRoleService roleService;

    @Autowired
    private BaseAuthorityService baseAuthorityService;

    @Autowired
    private BaseUserMapper baseUserMapper;

    /**
     * 默认超级管理员账号
     */
    public final static String ROOT = "admin";

    @Override
    public UserAccount login(String account) {
        if (StringUtils.isEmpty(account)) {
            return null;
        }
        // 获取BaseAccount 基础账户数据 默认用户名 扩展时修改
        BaseAccount baseAccount = baseAccountService.getAccount(account,"username");
        UserAccount userAccount = getUserAccount(baseAccount.getUserId());
        BeanUtils.copyProperties(baseAccount,userAccount);
        return userAccount;
    }

    @Override
    public UserAccount getUserAccount(Long userId) {
        // 用户权限列表
        List<VenusAuthority> authorities = Lists.newArrayList();
        // 用户角色列表
        List<Map> roles = Lists.newArrayList();
        // 数据库查询角色
        List<BaseRole> roleList = roleService.getUserRoles(userId);
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (BaseRole role : roleList) {
                Map roleMap = Maps.newHashMap();
                roleMap.put("roleId", role.getRoleId());
                roleMap.put("roleCode",role.getRoleCode());
                roleMap.put("roleName", role.getRoleName());
                // 角色详情
                roles.add(roleMap);
                // 加入角色标志
                VenusAuthority authority = new VenusAuthority(role.getRoleId().toString(),"ROLE_" + role.getRoleCode(),null,"role");
                authorities.add(authority);
            }
        }

        BaseUser baseUser = this.getById(userId);
        // 加入用户权限
        List<VenusAuthority> userGrantedAuthorities = baseAuthorityService.findAuthorityByUser(userId,ROOT.equals(baseUser.getUserName()));
        if (CollectionUtils.isNotEmpty(userGrantedAuthorities)) {
            authorities.addAll(userGrantedAuthorities);
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setNickName(baseUser.getNickName());
        userAccount.setAvatar(baseUser.getAvatar());
        userAccount.setAuthorities(authorities);
        userAccount.setRoles(roles);
        return userAccount;
    }

    @Override
    public List<BaseUser> findAllList() {
        List<BaseUser> list = baseUserMapper.selectList(new QueryWrapper<>());
        return list;
    }

    @Override
    public IPage<BaseUser> findListPage(PageParams pageParams) {
        BaseUser query = pageParams.mapToObject(BaseUser.class);
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(ObjectUtils.isNotEmpty(query.getUserId()), BaseUser::getUserId, query.getUserId())
                .eq(ObjectUtils.isNotEmpty(query.getUserType()), BaseUser::getUserType, query.getUserType())
                .like(ObjectUtils.isNotEmpty(query.getUserName()), BaseUser::getUserName, query.getUserName())
                .like(ObjectUtils.isNotEmpty(query.getMobile()), BaseUser::getMobile, query.getMobile());
        queryWrapper.orderByDesc("create_time");
        return baseUserMapper.selectPage(pageParams, queryWrapper);
    }

    @Override
    public BaseUser getUserByUsername(String username) {
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(BaseUser::getUserName, username);
        BaseUser saved = baseUserMapper.selectOne(queryWrapper);
        return saved;
    }

    @Override
    public void addUser(BaseUser user) {
        if (getUserByUsername(user.getUserName()) != null){
            throw new VenusAlertException("用户名: " + user.getUserName() + " 已存在!");
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
        baseUserMapper.insert(user);
        baseAccountService.register(user.getUserId(), user.getUserName(),user.getPassword(), BaseConstants.ACCOUNT_TYPE_USERNAME, user.getStatus(), BaseConstants.ACCOUNT_DOMAIN_ADMIN, null);
    }

    @Override
    public void updateUser(BaseUser user) {
        if (user ==null || user.getUserId() == null) {
            return;
        }
        if (user.getStatus() !=null) {
            baseAccountService.updateStatusByUserId(user.getUserId(), BaseConstants.ACCOUNT_DOMAIN_ADMIN, user.getStatus());
        }
        user.setUpdateTime(new Date());
        baseUserMapper.updateById(user);
    }

    @Override
    public BaseUser getUserById(Long userId) {
        return baseUserMapper.selectById(userId);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        baseAccountService.updatePasswordByUserId(userId,BaseConstants.ACCOUNT_DOMAIN_ADMIN,password);
    }


}
