package com.venus.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.venus.admin.common.model.PageParams;
import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.model.entity.BaseUser;
import com.venus.admin.model.vo.BaseUserVO;
import com.venus.admin.service.BaseRoleService;
import com.venus.admin.service.BaseUserService;
import com.venus.admin.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/6/30 15:19
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private BaseRoleService baseRoleService;

    @GetMapping("/user/all")
    public ResultBody<List<BaseUser>> getUserAllList() {
        return ResultBody.success().data(baseUserService.findAllList());
    }

    @GetMapping("/user")
    public ResultBody<IPage<BaseUser>> getUserList(@RequestParam(required = false) Map map) {
        return ResultBody.success().data(baseUserService.findListPage(new PageParams(map)));
    }

    @PostMapping("/user/add")
    public ResultBody<Long> addUser(@RequestBody BaseUserVO baseUserVO) {
        BaseUser user = new BaseUser();
        BeanUtils.copyProperties(baseUserVO,user);
        baseUserService.addUser(user);
        return ResultBody.success();
    }

    @PostMapping("/user/update")
    public ResultBody updateUser(@RequestBody BaseUserVO baseUserVO){
        BaseUser user = new BaseUser();
        BeanUtils.copyProperties(baseUserVO,user);
        baseUserService.updateUser(user);
        return ResultBody.success();
    }

    @PostMapping("/user/update/password")
    public ResultBody updatePassword(
            @RequestParam Long userId,
            @RequestParam String password
    ) {
      baseUserService.updatePassword(userId,password);
      return ResultBody.success();
    }

    @GetMapping("/user/roles")
    public ResultBody<List<BaseRole>> getUserRoles(@RequestParam Long userId) {
        return ResultBody.success().data(baseRoleService.getUserRoles(userId));
    }

    @PostMapping("/user/roles/add")
    public ResultBody addUserRoles(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "roleIds", required = false) String roleIds
    ) {
        baseRoleService.saveUserRoles(userId, StringUtils.isNotBlank(roleIds) ? roleIds.split(",") : new String[]{});
        return ResultBody.success();
    }

}
