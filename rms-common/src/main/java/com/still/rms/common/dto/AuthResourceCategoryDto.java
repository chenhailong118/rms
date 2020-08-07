package com.still.rms.common.dto;

import com.still.rms.mbg.model.AuthResourceCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author FishAndFlower
 * @Description 资源分类传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthResourceCategoryDto extends AuthResourceCategory {
    public interface AddAuthResourceCategoryGroup {}

    @ApiModelProperty(value = "分类名称")
    @NotNull(message = "分类名称不能为空")
    private String name;
}
