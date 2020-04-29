package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.model.UserAccount;
import com.venus.admin.model.entity.BaseUser;

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

    UserAccount getUserAccount(Long userId);

}
