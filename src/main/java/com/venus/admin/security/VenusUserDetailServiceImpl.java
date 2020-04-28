package com.venus.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Author: tcg
 * @Date: 2020/4/27 13:41
 * @Version 1.0
 */
@Slf4j
@Component(value = "userDetailsService")
public class VenusUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: " + username);
        // TODO 查询数据库
        VenusUserDetails userDetails = new VenusUserDetails();
        userDetails.setUserId(1L);
        userDetails.setUsername(username);
        userDetails.setPassword(passwordEncoder.encode("123456"));
        userDetails.setNickName("huashaoge");
        userDetails.setAuthorities(Collections.emptyList());
        userDetails.setAvatar("http://bpic.588ku.com/element_pic/18/05/21/2f03f76d187939a415c668c5ef70d380.jpg");
        userDetails.setAccountNonLocked(true);
        userDetails.setAccountNonExpired(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setEnabled(true);
        userDetails.setRoles(new String[]{"admin","editor"});
        return userDetails;
    }
}
