package com.still.aikandy.auth.service;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.dto.AuthRoleDto;
import com.still.aikandy.common.querycondition.AuthRoleQueryCondition;

import java.util.List;

/**
 * @Author Lee
 * @Description 角色Service接口
 * @Date 2020/6/24 16:18
 * @Version 1.0
 */
public interface RoleService {

    /**
     * 根据条件查询角色
     * @param authRoleQueryCondition 角色查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<AuthRoleDto>> queryAuthRoles(AuthRoleQueryCondition authRoleQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 根据条件查询角色
     * @param authRoleQueryCondition 角色查询条件
     * @return
     */
    List<AuthRoleDto> queryAuthRoles(AuthRoleQueryCondition authRoleQueryCondition);

    /**
     * 根据用户ID查询角色列表
     * @param userId 用户ID
     * @return
     */
    List<AuthRoleDto> getAuthRoleByUserId(Long userId);

    /**
     * 分配角色菜单关系
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @return
     */
    Integer updateRoleMenus(Long roleId, List<Long> menuIds);

    /**
     * 分配角色资源关系
     * @param roleId 角色ID
     * @param resourceIds 资源ID数组
     * @return
     */
    Integer updateRoleResource(Long roleId, List<Long> resourceIds);

    /**
     * 修改角色信息
     * @param roleId 角色ID
     * @param authRoleDto 角色信息
     * @return
     */
    Integer updateAuthRole(Long roleId, AuthRoleDto authRoleDto);

    /**
     * 创建角色信息
     * @param authRoleDto
     * @return
     */
    Integer addAuthRole(AuthRoleDto authRoleDto);

    /**
     * 删除角色信息
     * @param roleId 角色ID
     * @return
     */
    Integer deleteAuthRole(Long roleId);

    /**
     * 更新角色表中的用户数量字段
     */
    void updateRoleUserCount();
}
