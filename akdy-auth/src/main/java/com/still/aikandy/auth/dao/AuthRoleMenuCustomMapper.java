package com.still.aikandy.auth.dao;

import com.still.aikandy.mbg.model.AuthRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 角色菜单关系角色接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthRoleMenuCustomMapper {
    /**
     * 根据角色ID删除角色菜单关系
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 根据菜单ID删除角色菜单关系
     * @param menuId 菜单ID
     * @return
     */
    Integer deleteByMenuId(Long menuId);

    /**
     * 批量插入角色菜单关系
     */
    Integer insertList(@Param("list") List<AuthRoleMenu> authRoleMenuList);

}
