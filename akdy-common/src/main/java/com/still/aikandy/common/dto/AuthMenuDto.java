package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.AuthMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author Lee
 * @Description 菜单传输对象
 * @Date 2020/6/24 22:56
 * @Version 1.0
 */
@Data
public class AuthMenuDto extends AuthMenu {
    public interface AddAuthMenuGroup{}

    @ApiModelProperty(value = "父级ID")
    @NotNull(message = "父级ID不能为空", groups = {AddAuthMenuGroup.class})
    private Long parentId;

    @ApiModelProperty(value = "前端名称")
    @NotNull(message = "前端名称不能为空", groups = {AddAuthMenuGroup.class})
    private String name;

    @ApiModelProperty(value = "菜单名称")
    @NotNull(message = "菜单名称不能为空", groups = {AddAuthMenuGroup.class})
    private String title;

    @ApiModelProperty(value = "前端图标")
    @NotNull(message = "前端图标不能为空", groups = {AddAuthMenuGroup.class})
    private String icon;
}
