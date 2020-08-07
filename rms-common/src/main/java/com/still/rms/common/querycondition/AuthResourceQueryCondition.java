package com.still.rms.common.querycondition;

import com.still.rms.mbg.model.AuthResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author FishAndFlower
 * @Description 资源查询参数
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthResourceQueryCondition extends AuthResource {
    @ApiModelProperty(value = "资源名称关键词")
    private String nameKeyword;

    @ApiModelProperty(value = "资源URL关键词")
    private String urlKeyword;

}
