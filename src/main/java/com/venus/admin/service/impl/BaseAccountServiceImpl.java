package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.mapper.BaseAccountMapper;
import com.venus.admin.model.entity.BaseAccount;
import com.venus.admin.service.BaseAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tcg
 * @Date: 2020/4/29 11:02
 * @Version 1.0
 */
@Service
public class BaseAccountServiceImpl extends ServiceImpl<BaseAccountMapper, BaseAccount> implements BaseAccountService {


    @Autowired
    private BaseAccountMapper baseAccountMapper;

    @Override
    public BaseAccount getAccount(String account, String accountType) {
        QueryWrapper<BaseAccount> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(BaseAccount::getAccount, account)
                .eq(BaseAccount::getAccountType, accountType);
        return baseAccountMapper.selectOne(queryWrapper);
    }
}
