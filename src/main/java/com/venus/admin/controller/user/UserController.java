package com.venus.admin.controller.user;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.JwtToken;
import com.venus.admin.model.User;
import com.venus.admin.model.UserAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tcg
 * @Date: 2020/4/22 15:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/login")
    public ResultBody<JwtToken> login2(@RequestParam String username, @RequestParam String password) {
        log.info("用户名："+username+" , 密码："+password);
        JwtToken token = new JwtToken();
        token.setToken("22222222");
        return ResultBody.success().data(token).msg("登录成功");
    }

    @PostMapping("/login2")
    public ResultBody<JwtToken> login(UserAndPassword userAndPassword) {
        log.info("用户名："+userAndPassword.getUsername()+" , 密码："+userAndPassword.getPassword());
        JwtToken token = new JwtToken();
        token.setToken("22222222");
        return ResultBody.success().data(token).msg("登录成功");
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

}
