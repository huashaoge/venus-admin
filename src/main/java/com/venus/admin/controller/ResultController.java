package com.venus.admin.controller;

import com.venus.admin.common.model.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tcg
 * @Date: 2020/4/22 13:57
 * @Version 1.0
 */
@RestController
public class ResultController {

    @GetMapping("/result")
    public ResultBody getResult(){
        return ResultBody.success().msg("你好").data("ssss").put("111","222");
    }

}
