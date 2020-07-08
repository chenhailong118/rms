package com.still.aikandy.apigateway.feignclient;


import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserRoleDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author Lee
 * @Description 用户回调函数
 * @Date 2020/7/6 17:21
 * @Version 1.0
 */
@Component
public class AuthUserFcFallback implements AuthUserFc {

    @Override
    public RestResponse<Integer> addUser(AuthUserDto authUserDto) throws IOException {
        return RestResponse.error(RestCode.HYSTRIX_DONOTHING_ERROR);
    }

    @Override
    public RestResponse<Integer> deleteUser(Long userId) {
        return RestResponse.error(RestCode.HYSTRIX_DONOTHING_ERROR);
    }

    @Override
    public RestResponse<Integer> editUser(Long userId, AuthUserDto authUserDto) {
        return RestResponse.error(RestCode.HYSTRIX_DONOTHING_ERROR);
    }

    @Override
    public RestResponse<PageInfo> queryUsers(AuthUserQueryCondition authUserQueryCondition, Integer pageNum, Integer pageSize) {
        return RestResponse.error(RestCode.HYSTRIX_DONOTHING_ERROR);
    }

    @Override
    public RestResponse<Integer> updateUserRole(AuthUserRoleDto authUserRoleDto) {
        return RestResponse.error(RestCode.HYSTRIX_DONOTHING_ERROR);
    }
}
