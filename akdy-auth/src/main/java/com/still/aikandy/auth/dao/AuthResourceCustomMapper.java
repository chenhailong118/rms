package com.still.aikandy.auth.dao;

import com.still.aikandy.common.dto.AuthMenuDto;
import com.still.aikandy.common.dto.AuthResourceDto;
import com.still.aikandy.common.querycondition.AuthMenuQueryCondition;
import com.still.aikandy.common.querycondition.AuthResourceQueryCondition;

import java.util.List;

/**
 * @Author Lee
 * @Description 资源接口
 * @Date 2020/6/24 23:48
 * @Version 1.0
 */
public interface AuthResourceCustomMapper {

    List<AuthResourceDto> getAuthResourcesByCondition(AuthResourceQueryCondition authResourceQueryCondition);

    List<AuthResourceDto> getAuthResourceaByRoleId(Long roleId);

    Integer deleteByCategoryId(Long categoryId);
}
