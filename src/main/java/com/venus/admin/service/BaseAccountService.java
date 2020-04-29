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

}
