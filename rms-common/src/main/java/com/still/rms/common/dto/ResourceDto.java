package com.still.rms.common.dto;

import com.still.rms.mbg.model.Resource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 资源传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class ResourceDto extends Resource {

    @ApiModelProperty(value = "演员ID数组")
    private List<Long> actors;
}
