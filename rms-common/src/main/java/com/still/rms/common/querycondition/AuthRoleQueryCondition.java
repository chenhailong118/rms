package com.still.rms.common.querycondition;

import com.still.rms.mbg.model.AuthRole;
import lombok.Data;

/**
 * @Author FishAndFlower
 * @Description 角色查询条件
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthRoleQueryCondition extends AuthRole {
    //查询关键词
    private String keyword;
}
