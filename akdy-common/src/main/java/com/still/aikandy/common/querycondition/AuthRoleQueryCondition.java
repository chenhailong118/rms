package com.still.aikandy.common.querycondition;

import com.still.aikandy.mbg.model.AuthRole;
import lombok.Data;

/**
 * @Author Lee
 * @Description 角色查询条件
 * @Date 2020/6/24 22:00
 * @Version 1.0
 */
@Data
public class AuthRoleQueryCondition extends AuthRole {
    //查询关键词
    private String keyword;
}
