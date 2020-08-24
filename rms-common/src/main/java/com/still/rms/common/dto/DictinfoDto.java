package com.still.rms.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.still.rms.mbg.model.Dictinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DictinfoDto extends Dictinfo {
    @ApiModelProperty(value = "数据字典类型ID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long dicttypeId;

    @ApiModelProperty(value = "排序")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer order;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updateTime;

}