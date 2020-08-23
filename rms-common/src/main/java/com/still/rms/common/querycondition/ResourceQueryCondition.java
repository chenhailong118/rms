package com.still.rms.common.querycondition;

import com.still.rms.mbg.model.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @Author FishAndFlower
 * @Description 资源查询条件
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@ApiModel
@Data
public class ResourceQueryCondition extends Resource {

    @ApiModelProperty(value = "搜索关键词")
    private String keyWord;

    @ApiModelProperty(value = "起始发行日期")
    private Date issuingdateFrom;

    @ApiModelProperty(value = "截止发行日期")
    private Date issuingdateTo;

    @ApiModelProperty(value = "起始播放时长 单位:分钟")
    private Integer durationFrom;

    @ApiModelProperty(value = "截止播放时长 单位:分钟")
    private Integer durationTo;

    @ApiModelProperty(value = "起始得分")
    private Integer scoreFrom;

    @ApiModelProperty(value = "截止得分")
    private Integer scoreTo;

    @ApiModelProperty(value = "资源起始大小 单位Gb")
    private Double sizeFrom;

    @ApiModelProperty(value = "资源截止大小 单位Gb")
    private Double sizeTo;

    @ApiModelProperty(value = "排序方式")
    private String orderStr;

    @ApiModelProperty(value = "演员ID")
    private Integer actorId;

    @ApiModelProperty(value = "标签ID")
    private Integer tagId;
}
