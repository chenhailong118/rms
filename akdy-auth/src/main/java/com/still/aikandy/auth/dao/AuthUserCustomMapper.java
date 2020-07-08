package com.still.aikandy.auth.dao;

import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Lee
 * @Description 用户自定义Mapper接口
 * @Date 2020/5/23 22:31
 * @Version 1.0
 */
@Repository
public interface AuthUserCustomMapper {
    List<AuthUserDto> getAuthUsersByCondition(AuthUserQueryCondition authUserQueryCondition);
}
