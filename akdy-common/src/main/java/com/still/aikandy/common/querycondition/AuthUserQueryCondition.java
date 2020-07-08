package com.still.aikandy.common.querycondition;

import com.still.aikandy.mbg.model.AuthUser;
import lombok.Data;

/**
 * @Author Lee
 * @Description 用户查询条件
 * @Date 2020/6/22 18:01
 * @Version 1.0
 */
@Data
public class AuthUserQueryCondition extends AuthUser {
    //查询关键字
    private String keyword;
}
