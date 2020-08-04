package com.still.aikandy.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author FishAndFlower
 * @Description 用户登录参数
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthUserLoginParam {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;
}
