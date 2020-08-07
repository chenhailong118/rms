package com.still.rms.auth.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.rms.auth.dao.AuthResourceCategoryCustomMapper;
import com.still.rms.auth.dao.AuthResourceCustomMapper;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.api.ApiException;
import com.still.rms.common.dto.AuthResourceCategoryDto;
import com.still.rms.common.querycondition.AuthResourceCategoryQueryCondition;
import com.still.rms.mbg.mapper.AuthResourceCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源service实现类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    private final AuthResourceCategoryCustomMapper authResourceCategoryCustomMapper;
    private final AuthResourceCategoryMapper authResourceCategoryMapper;
    private final AuthResourceCustomMapper authResourceCustomMapper;

    public ResourceCategoryServiceImpl(AuthResourceCategoryCustomMapper authResourceCategoryCustomMapper, AuthResourceCategoryMapper authResourceCategoryMapper, AuthResourceCustomMapper authResourceCustomMapper) {
        this.authResourceCategoryCustomMapper = authResourceCategoryCustomMapper;
        this.authResourceCategoryMapper = authResourceCategoryMapper;
        this.authResourceCustomMapper = authResourceCustomMapper;
    }


    /**
     * 根据条件查询菜单分类
     * @param authResourceCategoryQueryCondition 菜单分类查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<AuthResourceCategoryDto>> queryAuthResourceCategorys(AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize,"sort desc");
        }else {
            PageHelper.orderBy("sort desc");
        }
        List<AuthResourceCategoryDto> users = authResourceCategoryCustomMapper.getAuthResourceCategorysByCondition(authResourceCategoryQueryCondition);
        return new PageInfo(users);
    }

    /**
     * 根据条件查询菜单分类
     * @param authResourceCategoryQueryCondition 菜单分类查询条件
     * @return
     */
    @Override
    public List<AuthResourceCategoryDto> queryAuthResourceCategorys(AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition) {
        PageHelper.orderBy("sort desc");
        return authResourceCategoryCustomMapper.getAuthResourceCategorysByCondition(authResourceCategoryQueryCondition);
    }

    /**
     * 添加资源分类信息
     * @param authResourceCategoryDto 资源分类信息
     * @return
     */
    @Override
    public Integer addAuthResourceCategory(AuthResourceCategoryDto authResourceCategoryDto) {
        if(authResourceCategoryDto.getSort() == null){
            authResourceCategoryDto.setSort(0);
        }
        authResourceCategoryDto.setCreateTime(new Date());
        return authResourceCategoryMapper.insert(authResourceCategoryDto);
    }

    /**
     * 更新资源分类信息
     * @param id 资源分类ID
     * @param authResourceCategoryDto 资源分类信息
     * @return
     */
    @Override
    public Integer updateAuthResourceCategory(Long id, AuthResourceCategoryDto authResourceCategoryDto) {
        if(authResourceCategoryMapper.selectByPrimaryKey(id) == null){
            throw new ApiException(ResultCode.RESOURCE_CATEGORY_NOT_FOUND);
        }
        authResourceCategoryDto.setId(id);
        return authResourceCategoryMapper.updateByPrimaryKeySelective(authResourceCategoryDto);
    }

    /**
     * 删除资源分类信息
     * @param id 资源分类ID
     * @return
     */
    @Override
    @Transactional
    public Integer deleteAuthResourceCategory(Long id) {
        if(authResourceCategoryMapper.selectByPrimaryKey(id) == null){
            throw new ApiException(ResultCode.RESOURCE_CATEGORY_NOT_FOUND);
        }
        //删除资源数据
        authResourceCustomMapper.deleteByCategoryId(id);
        //删除资源分类数据
        return authResourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
