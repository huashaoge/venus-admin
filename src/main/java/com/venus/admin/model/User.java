package com.venus.admin.model;

import lombok.Data;

/**
 * @Author: tcg
 * @Date: 2020/4/22 16:57
 * @Version 1.0
 */
@Data
public class User {
    private String[] roles = {"admin","editor"};
}
