package com.venus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.venus.admin.model.entity.BaseAccount;

/**
 * @Author: tcg
 * @Date: 2020/4/29 11:01
 * @Version 1.0
 */

public interface BaseAccountService extends IService<BaseAccount> {

    /**
     * 获取账号信息
     * @param account
     * @param accountType
     * @return
     */
    BaseAccount getAccount(String account, String accountType);

    /**
     * 查找账号是否存在
     * @param account
     * @param accountType
     * @param domain
     * @return
     */
    Boolean isExist(String account, String accountType, String domain);

    /**
     * 注册账号
     * @param userId
     * @param account
     * @param password
     * @param accountType
     * @param status
     * @param domain
     * @param registerIp
     * @return
     */
    BaseAccount register(Long userId, String account, String password, String accountType, Integer status, String domain, String registerIp);

    /**
     * 更新用户状态
     * @param userId
     * @param domain
     * @param status
     * @return
     */
    int updateStatusByUserId(Long userId, String domain, Integer status);

    /**
     * 重置用户密码
     * @param userId
     * @param domain
     * @param password
     * @return
     */
    int updatePasswordByUserId(Long userId, String domain, String password);
}
