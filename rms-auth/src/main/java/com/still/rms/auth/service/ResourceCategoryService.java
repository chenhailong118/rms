package com.still.rms.auth.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.AuthResourceCategoryDto;
import com.still.rms.common.querycondition.AuthResourceCategoryQueryCondition;

import java.util.List;
/**
 * @Author FishAndFlower
 * @Description 资源分类Service接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface ResourceCategoryService {

    /**
     * 根据条件查询资源
     * @param authResourceCategoryQueryCondition 资源查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<AuthResourceCategoryDto>> queryAuthResourceCategorys(AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 根据条件查询资源
     * @param authResourceCategoryQueryCondition 资源查询条件
     * @return
     */
    List<AuthResourceCategoryDto> queryAuthResourceCategorys(AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition);

    /**
     * 添加资源分类信息
     * @param authResourceCategoryDto 资源分类信息
     * @return
     */
    Integer addAuthResourceCategory(AuthResourceCategoryDto authResourceCategoryDto);

    /**
     * 更新资源分类信息
     * @param id 资源分类ID
     * @param authResourceCategoryDto 资源分类信息
     * @return
     */
    Integer updateAuthResourceCategory(Long id, AuthResourceCategoryDto authResourceCategoryDto);

    /**
     * 删除资源分类信息
     * @param id 资源分类ID
     * @return
     */
    Integer deleteAuthResourceCategory(Long id);
}
