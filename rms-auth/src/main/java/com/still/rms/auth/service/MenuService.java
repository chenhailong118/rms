package com.still.rms.auth.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.AuthMenuDto;
import com.still.rms.common.dto.AuthMenuTree;
import com.still.rms.common.querycondition.AuthMenuQueryCondition;
import com.still.rms.mbg.model.AuthMenu;

import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 菜单Service接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface MenuService {
    /**
     * 添加菜单信息
     * @param authMenuDto 菜单信息
     * @return
     */
    Integer addAuthUser(AuthMenuDto authMenuDto);

    /**
     * 根据菜单ID删除菜单信息
     * @param menuId 菜单信息
     * @return
     */
    Integer deleteAuthMenu(Long menuId);

    /**
     * 修改菜单信息
     * @param menuId 菜单ID
     * @param authMenuDto 菜单信息
     * @return
     */
    Integer updateAuthMenu(Long menuId, AuthMenuDto authMenuDto);

    /**
     * 根据用户ID查询用户菜单
     * @param userId 用户ID
     * @return
     */
    List<AuthMenuDto> getMenuBYUserId(Long userId);

    /**
     * 根据条件查询菜单
     * @param authMenuQueryCondition 菜单查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<AuthMenuDto>> queryAuthMenus(AuthMenuQueryCondition authMenuQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 根据条件查询菜单
     * @param authMenuQueryCondition 菜单查询条件
     * @return
     */
    List<AuthMenuDto> queryAuthMenus(AuthMenuQueryCondition authMenuQueryCondition);

    /**
     * 根据角色ID查询菜单列表
     * @param roleId 角色ID
     * @return
     */
    List<AuthMenuDto> getAuthMenuByRoleId(Long roleId);

    /**
     * 查询菜单数结构
     * @param authMenuQueryCondition
     * @return
     */
    List<AuthMenuTree> queryAuthMenusTree(AuthMenuQueryCondition authMenuQueryCondition);
}
