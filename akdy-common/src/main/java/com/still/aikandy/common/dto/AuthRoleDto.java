package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.AuthRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Author FishAndFlower
 * @Description 角色传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthRoleDto extends AuthRole {
    public interface AddAuthRoleGroup{};

    @ApiModelProperty(value = "名称")
    @NotNull(message = "角色名称不能为空", groups = {AddAuthRoleGroup.class})
    private String name;

    @ApiModelProperty(value = "描述")
    @NotNull(message = "角色描述不能为空", groups = {AddAuthRoleGroup.class})
    private String description;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    @Range(min = 0, max = 1, message = "启用状态：0->禁用；1->启用", groups = {AddAuthRoleGroup.class})
    private Integer status;
}
