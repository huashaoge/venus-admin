package com.venus.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.common.model.PageParams;
import com.venus.admin.model.UserAccount;
import com.venus.admin.model.entity.BaseUser;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/4/29 10:36
 * @Version 1.0
 */

public interface BaseUserService extends IService<BaseUser> {

    /**
     * 用于 UserDetailsService 查询登录信息返回匹配
     * @param account
     * @return
     */
    UserAccount login(String account);

    /**
     * 获取登录账户信息
     * @param userId
     * @return
     */
    UserAccount getUserAccount(Long userId);

    /**
     * 查询列表
     * @return
     */
    List<BaseUser> findAllList();

    /**
     * 分页查询
     * @param pageParams
     * @return
     */
    IPage<BaseUser> findListPage(PageParams pageParams);

    /**
     * 根据登录名查询系统用户信息
     * @param username
     * @return
     */
    BaseUser getUserByUsername(String username);

    /**
     * 添加用户信息
     * @param user
     */
    void addUser(BaseUser user);

    /**
     * 更新系统用户
     * @param user
     */
    void updateUser(BaseUser user);

    /**
     * 根据ID 获取用户
     * @param userId
     * @return
     */
    BaseUser getUserById(Long userId);

    /**
     * 更新密码
     * @param userId
     * @param password
     */
    void updatePassword(Long userId, String password);
}
