package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.AuthRoleMenu;
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
public class AuthRoleMenuDto extends AuthRoleMenu {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID数组")
    private List<Long> menuIds;
}
