package com.venus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venus.admin.mapper.BaseActionMapper;
import com.venus.admin.model.entity.BaseAction;
import com.venus.admin.service.BaseActionService;
import org.springframework.stereotype.Service;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:37
 * @Version 1.0
 */
@Service
public class BaseActionServiceImpl extends ServiceImpl<BaseActionMapper, BaseAction> implements BaseActionService {
}
