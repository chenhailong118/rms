package com.still.rms.auth.dao;

import com.still.rms.common.dto.AuthResourceCategoryDto;
import com.still.rms.common.querycondition.AuthResourceCategoryQueryCondition;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源分类接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthResourceCategoryCustomMapper {

    List<AuthResourceCategoryDto> getAuthResourceCategorysByCondition(AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition);
}
