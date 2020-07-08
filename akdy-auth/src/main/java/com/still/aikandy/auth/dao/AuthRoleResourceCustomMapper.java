package com.still.aikandy.auth.dao;

import com.still.aikandy.mbg.model.AuthRoleMenu;
import com.still.aikandy.mbg.model.AuthRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Lee
 * @Description 角色资源关系角色接口
 * @Date 2020/6/26 10:50
 * @Version 1.0
 */
public interface AuthRoleResourceCustomMapper {
    /**
     * 根据角色ID删除角色菜单关系
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 批量插入角色资源关系
     * @param relationList
     * @return
     */
    Integer insertList(List<AuthRoleResource> relationList);

//    /**
//     * 批量插入角色菜单关系
//     */
//    Integer insertList(@Param("list") List<AuthRoleMenu> authRoleMenuList);

}
