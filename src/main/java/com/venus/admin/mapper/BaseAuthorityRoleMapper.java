package com.venus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthorityRole;
import com.venus.admin.security.VenusAuthority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/11 11:46
 * @Version 1.0
 */
@Repository
public interface BaseAuthorityRoleMapper extends BaseMapper<BaseAuthorityRole> {
    /**
     * 根据角色获取权限菜单
     * @param roleId
     * @return
     */
    List<AuthorityMenu> selectAuthorityMenuByRole(@Param("roleId") Long roleId);

    /**
     * 获取角色已授权权限
     * @param roleId
     * @return
     */
    List<VenusAuthority> selectAuthorityByRole(@Param("roleId")Long roleId);
}
