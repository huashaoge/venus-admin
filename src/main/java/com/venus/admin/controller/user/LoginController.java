package com.venus.admin.controller.user;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.JwtToken;
import com.venus.admin.model.User;
import com.venus.admin.model.UserAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/4/22 15:52
 * @Version 1.0
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    AuthorizationServerEndpointsConfiguration endpoints;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login/token")
    public ResultBody<OAuth2AccessToken> login2(@RequestParam String username, @RequestParam String password) throws Exception {
        OAuth2AccessToken result = getToken(username, password);
        return ResultBody.success().data(result).msg("登录成功");
    }



    @GetMapping("/info")
    public ResultBody<User> GetUserInfo(){
        User user = new User();
        user.setRoles(new String[]{"admin","editor"});
        return ResultBody.success().data(user).msg("信息获取成功");
    }

    @PostMapping("/logout")
    public ResultBody logout(){
        return ResultBody.success();
    }

    /**
     * 内部开放提交获取token
     * @param username
     * @param password
     * @return
     */
    private OAuth2AccessToken getToken(String username, String password) throws Exception {
        Map<String,String> postParams = new HashMap<>();
        postParams.put("username", username);
        postParams.put("password", password);
        postParams.put("client_id", "admin");
        postParams.put("client_secret", "123456");
        postParams.put("grant_type", "password");
        // 配置或者数据库 TODO

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId("admin");
        if (clientDetails == null) {
            throw new NoSuchClientException("No client with requested id");
        }
        if (!passwordEncoder.matches("123456", clientDetails.getClientSecret())) {
            throw new InvalidClientException("Bad client credentials");
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("admin", "123456", Collections.emptyList());
        ResponseEntity<OAuth2AccessToken> responseEntity = endpoints.tokenEndpoint().postAccessToken(authRequest, postParams);
        return responseEntity.getBody();
    }

}
