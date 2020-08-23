package com.still.rms.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源标签传输对象
 * @Date 2020/8/22 11:56
 * @Version 1.0
 */
@Data
public class ResourceTagDto {

    @ApiModelProperty(value = "资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "标签ID数组")
    private List<Long> tagIds;
}
