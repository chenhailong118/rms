package com.still.aikandy.auth.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.dao.AuthResourceCustomMapper;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestException;
import com.still.aikandy.common.dto.AuthResourceDto;
import com.still.aikandy.common.querycondition.AuthResourceQueryCondition;
import com.still.aikandy.mbg.mapper.AuthResourceMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Lee
 * @Description 资源service实现类
 * @Date 2020/6/24 16:19
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final AuthResourceCustomMapper authResourceCustomMapper;
    private final AuthResourceMapper authResourceMapper;

    public ResourceServiceImpl(AuthResourceCustomMapper authResourceCustomMapper, AuthResourceMapper authResourceMapper) {
        this.authResourceCustomMapper = authResourceCustomMapper;
        this.authResourceMapper = authResourceMapper;
    }


    /**
     * 根据条件查询菜单
     * @param authResourceQueryCondition 菜单查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<AuthResourceDto>> queryAuthResources(AuthResourceQueryCondition authResourceQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<AuthResourceDto> users = authResourceCustomMapper.getAuthResourcesByCondition(authResourceQueryCondition);
        return new PageInfo(users);
    }

    /**
     * 根据条件查询资源（ALL）
     * @param authResourceQueryCondition 资源查询条件
     * @return
     */
    @Override
    public List<AuthResourceDto> queryAuthResources(AuthResourceQueryCondition authResourceQueryCondition) {
        return authResourceCustomMapper.getAuthResourcesByCondition(authResourceQueryCondition);
    }

    /**
     * 根据角色ID获取资源列表
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<AuthResourceDto> getAuthResourceaByRoleId(Long roleId) {
        return authResourceCustomMapper.getAuthResourceaByRoleId(roleId);
    }

    /**
     * 添加资源信息
     * @param authResourceDto 资源信息
     * @return
     */
    @Override
    public Integer addAuthResource(AuthResourceDto authResourceDto) {
        authResourceDto.setCreateTime(new Date());
        return authResourceMapper.insert(authResourceDto);
    }

    /**
     * 更新资源信息
     * @param id 资源ID
     * @param authResourceDto 资源信息
     * @return
     */
    @Override
    public Integer updateAuthResource(Long id, AuthResourceDto authResourceDto) {
        if(authResourceMapper.selectByPrimaryKey(id) == null) {
            throw new RestException(RestCode.RESOURCE_NOT_FOUND);
        }
        authResourceDto.setId(id);
        return authResourceMapper.updateByPrimaryKeySelective(authResourceDto);
    }

    /**
     * 删除资源信息
     * @param id 资源ID
     * @return
     */
    @Override
    public Integer deleteAuthResource(Long id) {
        if(authResourceMapper.selectByPrimaryKey(id) == null){
            throw new RestException(RestCode.RESOURCE_NOT_FOUND);
        }
        return authResourceMapper.deleteByPrimaryKey(id);
    }
}