package com.venus.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.venus.admin.common.model.PageParams;
import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.model.entity.BaseRoleUser;
import com.venus.admin.model.vo.BaseRoleVO;
import com.venus.admin.service.BaseRoleService;
import com.venus.admin.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/6/22 14:23
 * @Version 1.0
 */
@RestController
public class RoleController {

    @Autowired
    private BaseRoleService baseRoleService;

    @GetMapping("/role")
    public ResultBody<IPage<BaseRole>> getRoleListPage(@RequestParam(required = false) Map map) {
        return ResultBody.success().data(baseRoleService.findListPage(new PageParams(map)));
    }

    @PostMapping("/role/add")
    public ResultBody<Long> addRole(@RequestBody @Validated BaseRoleVO baseRoleVO) {
        BaseRole role = new BaseRole();
        BeanUtils.copyProperties(baseRoleVO,role);
        BaseRole result = baseRoleService.addRole(role);
        Long roleId = null;
        if (result != null) {
            roleId = result.getRoleId();
        }
        return ResultBody.success().data(roleId);
    }

    @PostMapping("/role/update")
    public ResultBody updateRole(@RequestBody @Validated BaseRoleVO baseRoleVO) {
        BaseRole role = new BaseRole();
        BeanUtils.copyProperties(baseRoleVO,role);
        baseRoleService.updateRole(role);
        return ResultBody.success();
    }

    @PostMapping("/role/delete")
    public ResultBody deleteRole(@RequestParam("roleId") Long roleId) {
        baseRoleService.deleteRole(roleId);
        return ResultBody.success();
    }

    @GetMapping("/role/users")
    public ResultBody<List<BaseRoleUser>> getRoleUsers(@RequestParam("roleId") Long roleId) {
        return ResultBody.success().data(baseRoleService.findRoleUsers(roleId));
    }

    @PostMapping("/role/users/add")
    public ResultBody addUserRoles(
            @RequestParam(value = "roleId") Long roleId,
            @RequestParam(value = "userIds", required = false) String userIds
    ) {
        baseRoleService.saveRoleUsers(roleId, StringUtils.isNotBlank(userIds) ? userIds.split(",") : new String[]{});
        return ResultBody.success();
    }

    @GetMapping("/role/all")
    public ResultBody<List<BaseRole>> getRoleAllList() {
        return ResultBody.success().data(baseRoleService.findAllList());
    }

}
