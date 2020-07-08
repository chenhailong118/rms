package com.still.aikandy.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author Lee
 * @Description 用户登录参数
 * @Date 2020/6/23 13:39
 * @Version 1.0
 */
@Data
public class AuthUserLoginParam {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;
}
