package com.venus.admin.model;

import lombok.Data;

/**
 * @Author: tcg
 * @Date: 2020/4/22 16:08
 * @Version 1.0
 */
@Data
public class JwtToken {

    private String token;

    private long expireIn = System.currentTimeMillis();

}
