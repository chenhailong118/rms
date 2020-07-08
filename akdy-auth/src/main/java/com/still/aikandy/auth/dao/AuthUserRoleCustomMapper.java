package com.still.aikandy.auth.dao;

import com.still.aikandy.mbg.model.AuthUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Lee
 * @Description 用户角色关系角色接口
 * @Date 2020/6/24 16:04
 * @Version 1.0
 */
public interface AuthUserRoleCustomMapper {

    /**
     * 根据用户ID删除用户角色关系
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 根据角色ID删除用户角色关系
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<AuthUserRole> authUserRoleList);

    /**
     * 统计角色用户数量
     * @return
     */
    List<Map<String, Object>> getUserCount();
}
