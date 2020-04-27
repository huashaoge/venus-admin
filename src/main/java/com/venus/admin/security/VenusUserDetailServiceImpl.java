package com.venus.admin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("the user is not fund");
        } else {
            // TODO 用户角色数据库查询
            String role = "ROLE_ADMIN";
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            String pwd = passwordEncoder.encode("123456");
            return new User(username, pwd, authorities);
        }
    }
}
