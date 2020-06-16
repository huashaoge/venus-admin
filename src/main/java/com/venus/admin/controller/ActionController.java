package com.venus.admin.controller;

import com.venus.admin.common.model.ResultBody;
import com.venus.admin.model.entity.BaseAction;
import com.venus.admin.model.vo.BaseActionVO;
import com.venus.admin.service.BaseActionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tcg
 * @Date: 2020/6/16 15:22
 * @Version 1.0
 */
@RestController
public class ActionController {

    @Autowired
    private BaseActionService baseActionService;

    @PostMapping("/action/add")
    public ResultBody<Long> addAction(@RequestBody @Validated BaseActionVO baseActionVO){
        BaseAction baseAction = new BaseAction();
        BeanUtils.copyProperties(baseActionVO,baseAction);
        BaseAction result = baseActionService.addAction(baseAction);
        Long actionId = null;
        if (result != null) {
            actionId = result.getActionId();
        }
        return ResultBody.success().data(actionId);
    }
}
