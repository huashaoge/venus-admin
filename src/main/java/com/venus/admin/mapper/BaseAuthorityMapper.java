package com.venus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.venus.admin.model.AuthorityAction;
import com.venus.admin.model.AuthorityMenu;
import com.venus.admin.model.entity.BaseAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/5/6 16:12
 * @Version 1.0
 */
@Repository
public interface BaseAuthorityMapper extends BaseMapper<BaseAuthority> {
    /**
     * 获取菜单权限
     * @param map
     * @return
     */
    List<AuthorityMenu> selectAuthorityMenu(Map map);

    /**
     * 获取操作权限
     * @param map
     * @return
     */
    List<AuthorityAction> selectAuthorityAction(Map map);
}
