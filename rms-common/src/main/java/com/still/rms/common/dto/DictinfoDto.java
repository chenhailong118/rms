package com.still.rms.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.still.rms.mbg.model.Dictinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DictinfoDto extends Dictinfo {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "数据字典类型ID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer dicttypeId;

    @ApiModelProperty(value = "字典信息")
    private String name;

    @ApiModelProperty(value = "排序")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer order;

    @ApiModelProperty(value = "父节点ID，根节点为0")
    private Integer parentId;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updateTime;

}