package com.still.rms.common.dto;

import com.still.rms.mbg.model.AuthResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @Author FishAndFlower
 * @Description 资源传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthResourceDto extends AuthResource {
    public interface AddAuthResourceGroup {}

    @ApiModelProperty(value = "资源分类ID")
    @NotNull(message = "资源分类ID不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "资源名称")
    @NotNull(message = "资源名称不能为空")
    private String name;

    @ApiModelProperty(value = "资源URL")
    @NotNull(message = "资源URL不能为空")
    private String url;
}
