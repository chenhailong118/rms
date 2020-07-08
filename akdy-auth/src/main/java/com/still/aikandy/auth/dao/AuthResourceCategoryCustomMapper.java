package com.still.aikandy.auth.dao;

import com.still.aikandy.common.dto.AuthResourceCategoryDto;
import com.still.aikandy.common.querycondition.AuthResourceCategoryQueryCondition;

import java.util.List;

/**
 * @Author Lee
 * @Description 资源分类接口
 * @Date 2020/6/24 23:48
 * @Version 1.0
 */
public interface AuthResourceCategoryCustomMapper {

    List<AuthResourceCategoryDto> getAuthResourceCategorysByCondition(AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition);
}
