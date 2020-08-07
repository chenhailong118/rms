package com.still.rms.auth.dao;

import com.still.rms.common.dto.AuthResourceDto;
import com.still.rms.common.querycondition.AuthResourceQueryCondition;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthResourceCustomMapper {

    List<AuthResourceDto> getAuthResourcesByCondition(AuthResourceQueryCondition authResourceQueryCondition);

    List<AuthResourceDto> getAuthResourceaByRoleId(Long roleId);

    Integer deleteByCategoryId(Long categoryId);
}
