package com.still.rms.common.dto;

import com.still.rms.mbg.model.AuthMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author FishAndFlower
 * @Description 菜单传输对象
 * @Date 2020/8/4 10:51
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
