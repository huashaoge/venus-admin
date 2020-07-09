package com.venus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.venus.admin.model.entity.BaseRole;
import com.venus.admin.model.entity.BaseRoleUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: tcg
 * @Date: 2020/5/11 11:15
 * @Version 1.0
 */
@Repository
public interface BaseRoleUserMapper extends BaseMapper<BaseRoleUser> {
    /**
     * 通过用户id查询相关角色
     * @param userId
     * @return
     */
    List<BaseRole> selectRoleUserList(@Param("userId") Long userId);

    /**
     * 查询用户角色ID列表
     * @param userId
     * @return
     */
    List<Long> selectRoleUserIdList(@Param("userId") Long userId);
}
