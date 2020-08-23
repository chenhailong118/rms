package com.still.rms.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author FishAndFlower
 * @Description TODO
 * @Date 2020/8/23 10:21
 * @Version 1.0
 */
@Data
public class ActorIdAndName {

    @ApiModelProperty(value = "演员id")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;
}
