package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.AuthRoleResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Lee
 * @Description 角色菜单关系传输对象
 * @Date 2020/6/26 10:34
 * @Version 1.0
 */
@Data
public class AuthRoleResourceDto extends AuthRoleResource {

    @ApiModelProperty(value = "资源ID数组")
    private List<Long> resourceIds;
}
