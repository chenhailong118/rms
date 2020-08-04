package com.still.aikandy.auth.dao;

import com.still.aikandy.common.dto.AuthRoleDto;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.querycondition.AuthRoleQueryCondition;
import com.still.aikandy.mbg.model.AuthMenu;

import java.util.List;
import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description 角色接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthRoleCustomMapper {

    /**
     * 根据条件查询角色
     * @param authRoleQueryCondition
     * @return
     */
    List<AuthRoleDto> getAuthRolesByCondition(AuthRoleQueryCondition authRoleQueryCondition);

    /**
     * 根据用户ID查询角色列表
     * @param userId
     * @return
     */
    List<AuthRoleDto> getAuthRoleByUserId(Long userId);
}
