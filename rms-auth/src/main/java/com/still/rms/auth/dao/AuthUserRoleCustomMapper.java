package com.still.rms.auth.dao;

import com.still.rms.mbg.model.AuthUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description 用户角色关系角色接口
 * @Date 2020/8/4 10:51
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
