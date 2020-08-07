package com.still.rms.common.dto;

import com.still.rms.mbg.model.AuthRoleResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 角色菜单关系传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthRoleResourceDto extends AuthRoleResource {

    @ApiModelProperty(value = "资源ID数组")
    private List<Long> resourceIds;
}
