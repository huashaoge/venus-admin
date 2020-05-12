package com.venus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthorityUser;
import com.venus.admin.security.VenusAuthority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/11 15:29
 * @Version 1.0
 */
@Repository
public interface BaseAuthorityUserMapper extends BaseMapper<BaseAuthorityUser> {
    /**
     * 根据用户id获取与用户绑定的权限目录
     * @param userId
     * @return
     */
    List<AuthorityMenu> selectAuthorityMenuByUser(@Param("userId") Long userId);

    /**
     * 获取用户已授权权限
     * @param userId
     * @return
     */
    List<VenusAuthority> selectAuthorityByUser(@Param("userId") Long userId);
}
