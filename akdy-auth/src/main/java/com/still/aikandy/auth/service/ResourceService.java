package com.still.aikandy.auth.service;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.dto.AuthMenuDto;
import com.still.aikandy.common.dto.AuthResourceDto;
import com.still.aikandy.common.querycondition.AuthMenuQueryCondition;
import com.still.aikandy.common.querycondition.AuthResourceQueryCondition;

import java.util.List;

/**
 * @Author Lee
 * @Description 资源Service接口
 * @Date 2020/6/24 16:18
 * @Version 1.0
 */
public interface ResourceService {

    /**
     * 根据条件查询资源(分页)
     * @param authResourceQueryCondition 资源查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<AuthResourceDto>> queryAuthResources(AuthResourceQueryCondition authResourceQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 根据条件查询资源（ALL）
     * @param authResourceQueryCondition 资源查询条件
     * @return
     */
    List<AuthResourceDto> queryAuthResources(AuthResourceQueryCondition authResourceQueryCondition);

    /**
     * 根据角色ID获取资源列表
     * @param roleId 角色ID
     * @return
     */
    List<AuthResourceDto> getAuthResourceaByRoleId(Long roleId);

    /**
     * 添加资源信息
     * @param authResourceDto 资源信息
     * @return
     */
    Integer addAuthResource(AuthResourceDto authResourceDto);

    /**
     * 更新资源信息
     * @param id 资源ID
     * @param authResourceDto 资源信息
     * @return
     */
    Integer updateAuthResource(Long id, AuthResourceDto authResourceDto);

    /**
     * 删除资源信息
     * @param id 资源ID
     * @return
     */
    Integer deleteAuthResource(Long id);
}
