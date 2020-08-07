package com.still.rms.auth.dao;

import com.still.rms.common.dto.AuthUserDto;
import com.still.rms.common.querycondition.AuthUserQueryCondition;
import com.still.rms.mbg.model.AuthResource;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Author FishAndFlower
 * @Description 用户自定义Mapper接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Repository
public interface AuthUserCustomMapper {
    List<AuthUserDto> getAuthUsersByCondition(AuthUserQueryCondition authUserQueryCondition);

    List<AuthResource> getResourcesByUserId(Long userId);

    Integer updateByPrimaryKey(AuthUserDto authUserDto);
}
