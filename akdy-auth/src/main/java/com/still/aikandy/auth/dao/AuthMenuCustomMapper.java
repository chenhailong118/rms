package com.still.aikandy.auth.dao;

import com.still.aikandy.common.dto.AuthMenuDto;
import com.still.aikandy.common.querycondition.AuthMenuQueryCondition;
import com.still.aikandy.mbg.model.AuthMenu;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 菜单接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthMenuCustomMapper {
    /**
     * 根据用户id查询菜单信息
     * @param userId
     * @return
     */
    List<AuthMenuDto> getMenuBYUserId(Long userId);

    List<AuthMenuDto> getAuthMenusByCondition(AuthMenuQueryCondition authMenuQueryCondition);

    List<AuthMenuDto> getAuthMenuByRoleId(Long roleId);

}
