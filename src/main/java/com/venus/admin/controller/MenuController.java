package com.venus.admin.controller;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.model.vo.BaseMenuVO;
import com.venus.admin.service.BaseMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/13 14:36
 * @Version 1.0
 */
@RestController
public class MenuController {
    @Autowired
    private BaseMenuService baseMenuService;

    @GetMapping("/menu/all")
    public ResultBody<List<BaseMenu>> getMenuAll() {
        List<BaseMenu> menuAll = baseMenuService.list();
        menuAll.sort(Comparator.comparing(BaseMenu::getPriority));
        return ResultBody.success().data(menuAll);
    }

    @PostMapping("/menu/update")
    public ResultBody updateMenu(@RequestBody @Validated BaseMenuVO menuVO) {
        BaseMenu baseMenu = new BaseMenu();
        BeanUtils.copyProperties(menuVO,baseMenu);
        baseMenuService.updateById(baseMenu);
        return ResultBody.success();
    }

    @PostMapping("/menu/add")
    public ResultBody addMenu(@RequestBody @Validated BaseMenuVO menuVO) {
        BaseMenu baseMenu = new BaseMenu();
        BeanUtils.copyProperties(menuVO,baseMenu);
        BaseMenu result = baseMenuService.addMenu(baseMenu);
        Long menuId = null;
        if (result != null) {
            menuId = result.getMenuId();
        }
        return ResultBody.success().data(menuId);
    }

    @PostMapping("/menu/delete")
    public ResultBody deleteMenu(@RequestParam("menuId") Long menuId) {
        baseMenuService.deleteMenu(menuId);
        return ResultBody.success();
    }

}
