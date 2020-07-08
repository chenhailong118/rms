package com.still.aikandy.common.querycondition;

import com.still.aikandy.mbg.model.AuthResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Lee
 * @Description 资源查询参数
 * @Date 2020/6/24 23:47
 * @Version 1.0
 */
@Data
public class AuthResourceQueryCondition extends AuthResource {
    @ApiModelProperty(value = "资源名称关键词")
    private String nameKeyword;

    @ApiModelProperty(value = "资源URL关键词")
    private String urlKeyword;

}
