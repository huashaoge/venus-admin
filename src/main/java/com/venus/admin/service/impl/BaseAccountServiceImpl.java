package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.common.constants.BaseConstants;
import com.venus.admin.exception.VenusAlertException;
import com.venus.admin.mapper.BaseAccountMapper;
import com.venus.admin.model.entity.BaseAccount;
import com.venus.admin.service.BaseAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: tcg
 * @Date: 2020/4/29 11:02
 * @Version 1.0
 */
@Service
public class BaseAccountServiceImpl extends ServiceImpl<BaseAccountMapper, BaseAccount> implements BaseAccountService {


    @Autowired
    private BaseAccountMapper baseAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseAccount getAccount(String account, String accountType) {
        QueryWrapper<BaseAccount> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(BaseAccount::getAccount, account)
                .eq(BaseAccount::getAccountType, accountType);
        return baseAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean isExist(String account, String accountType, String domain) {
        QueryWrapper<BaseAccount> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(BaseAccount::getAccount, account)
                .eq(BaseAccount::getAccountType, accountType)
                .eq(BaseAccount::getDomain, domain);
        int count = baseAccountMapper.selectCount(queryWrapper);
        return count > 0 ? true : false;
    }

    @Override
    public BaseAccount register(Long userId, String account, String password, String accountType, Integer status, String domain, String registerIp) {
        if (isExist(account, accountType, domain)){
            // 账号已被注册
            throw new VenusAlertException(String.format("account=[%s],domain=[%s]",account, domain));
        }
        // 加密
        String encodePassword = passwordEncoder.encode(password);
        BaseAccount baseAccount = new BaseAccount(userId, account, encodePassword, accountType, domain, registerIp);
        baseAccount.setCreateTime(new Date());
        baseAccount.setUpdateTime(baseAccount.getCreateTime());
        baseAccount.setStatus(status);
        baseAccountMapper.insert(baseAccount);
        return baseAccount;
    }

    @Override
    public int updateStatusByUserId(Long userId, String domain, Integer status) {
        if (status == null) {
            return 0;
        }
        BaseAccount baseAccount = new BaseAccount();
        baseAccount.setUpdateTime(new Date());
        baseAccount.setStatus(status);
        QueryWrapper<BaseAccount> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BaseAccount::getDomain, domain)
                .eq(BaseAccount::getUserId, userId);
        return baseAccountMapper.update(baseAccount, queryWrapper);
    }

    @Override
    public int updatePasswordByUserId(Long userId, String domain, String password) {
        BaseAccount baseAccount = new BaseAccount();
        baseAccount.setUpdateTime(new Date());
        baseAccount.setPassword(passwordEncoder.encode(password));
        QueryWrapper<BaseAccount> wrapper = new QueryWrapper();
        wrapper.lambda()
                .in(BaseAccount::getAccountType, BaseConstants.ACCOUNT_TYPE_USERNAME,BaseConstants.ACCOUNT_TYPE_EMAIL, BaseConstants.ACCOUNT_TYPE_MOBILE)
                .eq(BaseAccount::getUserId, userId)
                .eq(BaseAccount::getDomain, domain);
        return baseAccountMapper.update(baseAccount,wrapper);
    }
}
