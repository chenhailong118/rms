package com.still.aikandy.common.querycondition;

import com.still.aikandy.mbg.model.AuthUser;
import lombok.Data;

/**
 * @Author FishAndFlower
 * @Description 用户查询条件
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthUserQueryCondition extends AuthUser {
    //查询关键字
    private String keyword;
}
