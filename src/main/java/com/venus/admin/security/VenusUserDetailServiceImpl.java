package com.venus.admin.security;

import com.venus.admin.model.UserAccount;
import com.venus.admin.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: tcg
 * @Date: 2020/4/27 13:41
 * @Version 1.0
 */
@Slf4j
@Component(value = "userDetailsService")
public class VenusUserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private BaseUserService baseUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: " + username);
        //  查询数据库
        UserAccount account = baseUserService.login(username);
        if (account == null || account.getAccountId() == null) {
            throw new UsernameNotFoundException("系统用户 " + username + " 不存在!");
        }
        Long accountId = account.getAccountId();
        Long userId = account.getUserId();
        String password = account.getPassword();
        String nickName = account.getNickName();
        String avatar = account.getAvatar();
        String accountType = account.getAccountType();
        // status 状态码 2 为锁定
        boolean accountNonLocked = account.getStatus().intValue() != 2;
        boolean credentialsNonExpired = true;
        // 1 为有效
        boolean enabled = account.getStatus().intValue() == 1 ? true:false;
        boolean accountNonExpired = true;

        VenusUserDetails userDetails = new VenusUserDetails();
        userDetails.setAccountId(accountId);
        userDetails.setAccountType(accountType);
        userDetails.setUserId(userId);
        userDetails.setUsername(username);
        userDetails.setPassword(password);
        userDetails.setNickName(nickName);
        userDetails.setAuthorities(account.getAuthorities());
        userDetails.setAvatar(avatar);
        userDetails.setAccountNonLocked(accountNonLocked);
        userDetails.setAccountNonExpired(accountNonExpired);
        userDetails.setCredentialsNonExpired(credentialsNonExpired);
        userDetails.setEnabled(enabled);
        return userDetails;
    }
}
