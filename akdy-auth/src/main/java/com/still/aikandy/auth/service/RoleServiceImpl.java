package com.still.aikandy.auth.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.dao.AuthRoleCustomMapper;
import com.still.aikandy.auth.dao.AuthRoleMenuCustomMapper;
import com.still.aikandy.auth.dao.AuthRoleResourceCustomMapper;
import com.still.aikandy.auth.dao.AuthUserRoleCustomMapper;
import com.still.aikandy.common.api.ResultCode;
import com.still.aikandy.common.api.ApiException;
import com.still.aikandy.common.dto.AuthRoleDto;
import com.still.aikandy.common.querycondition.AuthRoleQueryCondition;
import com.still.aikandy.mbg.mapper.AuthRoleMapper;
import com.still.aikandy.mbg.model.AuthRole;
import com.still.aikandy.mbg.model.AuthRoleMenu;
import com.still.aikandy.mbg.model.AuthRoleResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Author FishAndFlower
 * @Description 角色service实现类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final AuthRoleCustomMapper authRoleCustomMapper;
    private final AuthRoleMenuCustomMapper authRoleMenuCustomMapper;
    private final AuthRoleResourceCustomMapper authRoleResourceCustomMapper;
    private final AuthUserRoleCustomMapper authUserRoleCustomMapper;
    private final AuthRoleMapper authRoleMapper;

    public RoleServiceImpl(AuthRoleCustomMapper authRoleCustomMapper, AuthRoleMenuCustomMapper authRoleMenuCustomMapper, AuthRoleResourceCustomMapper authRoleResourceCustomMapper, AuthUserRoleCustomMapper authUserRoleCustomMapper, AuthRoleMapper authRoleMapper) {
        this.authRoleCustomMapper = authRoleCustomMapper;
        this.authRoleMenuCustomMapper = authRoleMenuCustomMapper;
        this.authRoleResourceCustomMapper = authRoleResourceCustomMapper;
        this.authUserRoleCustomMapper = authUserRoleCustomMapper;
        this.authRoleMapper = authRoleMapper;
    }


    /**
     * 根据条件查询角色（分页）
     * @param authRoleQueryCondition 角色查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<AuthRoleDto>> queryAuthRoles(AuthRoleQueryCondition authRoleQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize,"sort desc");
        }else {
            PageHelper.orderBy("sort desc");
        }
        List<AuthRoleDto> users = authRoleCustomMapper.getAuthRolesByCondition(authRoleQueryCondition);
        return new PageInfo(users);
    }

    /**
     * 根据条件查询角色（不分页）
     * @param authRoleQueryCondition 角色查询条件
     * @return
     */
    @Override
    public List<AuthRoleDto> queryAuthRoles(AuthRoleQueryCondition authRoleQueryCondition) {
        PageHelper.orderBy("sort desc");
        return authRoleCustomMapper.getAuthRolesByCondition(authRoleQueryCondition);
    }

    /**
     * 根据用户ID查询角色列表
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<AuthRoleDto> getAuthRoleByUserId(Long userId) {
        return authRoleCustomMapper.getAuthRoleByUserId(userId);
    }

    /**
     * 分配角色菜单关系
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @return
     */
    @Override
    @Transactional
    public Integer updateRoleMenus(Long roleId, List<Long> menuIds) {
        //删除角色旧菜单关系
        authRoleMenuCustomMapper.deleteByRoleId(roleId);

        //建立用户角色关系
        if(!CollectionUtil.isEmpty(menuIds)){
            List<AuthRoleMenu> relationList = new ArrayList<>();
            menuIds.forEach(menuId -> {
                AuthRoleMenu relation = new AuthRoleMenu();
                relation.setRoleId(roleId);
                relation.setMenuId(menuId);
                relationList.add(relation);
            });
            return authRoleMenuCustomMapper.insertList(relationList);
        }
        return 0;
    }

    /**
     * 分配角色资源关系
     * @param roleId 角色ID
     * @param resourceIds 资源ID数组
     * @return
     */
    @Override
    @Transactional
    public Integer updateRoleResource(Long roleId, List<Long> resourceIds) {
        //删除旧角色资源关系
        authRoleResourceCustomMapper.deleteByRoleId(roleId);
        //建立新的角色资源关系
        if(!CollectionUtil.isEmpty(resourceIds)){
            List<AuthRoleResource> relationList = new ArrayList<>();
            resourceIds.forEach(resourceId ->{
                AuthRoleResource relation = new AuthRoleResource();
                relation.setRoleId(roleId);
                relation.setResourceId(resourceId);
                relationList.add(relation);
            });
            return authRoleResourceCustomMapper.insertList(relationList);
        }
        return null;
    }

    /**
     * 修改角色信息
     * @param roleId 角色ID
     * @param authRoleDto 角色信息
     * @return
     */
    @Override
    public Integer updateAuthRole(Long roleId, AuthRoleDto authRoleDto) {
        if(authRoleMapper.selectByPrimaryKey(roleId) == null){
            throw new ApiException(ResultCode.ROLE_NOT_FOUND);
        }
        authRoleDto.setId(roleId);
        return authRoleMapper.updateByPrimaryKeySelective(authRoleDto);
    }

    /**
     * 创建角色信息
     * @param authRoleDto 角色信息
     * @return
     */
    @Override
    public Integer addAuthRole(AuthRoleDto authRoleDto) {
        authRoleDto.setCreateTime(new Date());
        if(authRoleDto.getSort() == null){
            authRoleDto.setSort(0);
        }
        authRoleDto.setUserCount(0);
        return authRoleMapper.insertSelective(authRoleDto);
    }

    /**
     * 删除角色信息
     * @param roleId 角色ID
     * @return
     */
    @Override
    @Transactional
    public Integer deleteAuthRole(Long roleId) {
        //删除用户角色关系信息
        authUserRoleCustomMapper.deleteByRoleId(roleId);
        //删除角色菜单关系信息
        authRoleMenuCustomMapper.deleteByRoleId(roleId);
        //删除角色资源关系信息
        authRoleResourceCustomMapper.deleteByRoleId(roleId);
        //删除角色信息
        return authRoleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * 更新角色表中的用户数量字段
     */
    @Override
    public void updateRoleUserCount() {
        List<Map<String,Object>> userCount = authUserRoleCustomMapper.getUserCount();
        userCount.stream().forEach(map -> {
            AuthRole authRole = new AuthRole();
            authRole.setId((Long) map.get("roleId"));
            authRole.setUserCount(((Long) map.get("userCount")).intValue());
            authRoleMapper.updateByPrimaryKeySelective(authRole);
        });
    }
}
