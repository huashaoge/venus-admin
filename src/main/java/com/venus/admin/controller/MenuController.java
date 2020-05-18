package com.venus.admin.controller;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.entity.BaseMenu;
import com.venus.admin.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/menu/delete")
    public ResultBody deleteMenu(@RequestParam("menuId") Long menuId) {
        baseMenuService.deleteMenu(menuId);
        return ResultBody.success();
    }
}
