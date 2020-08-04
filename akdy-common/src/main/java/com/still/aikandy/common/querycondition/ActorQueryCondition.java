package com.still.aikandy.common.querycondition;

import com.still.aikandy.mbg.model.Actor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @Author FishAndFlower
 * @Description 演员查询条件
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@ApiModel
@Data
public class ActorQueryCondition extends Actor {

    @ApiModelProperty(value = "出生日期From，格式yyyy-MM-dd", position = 1)
    private Date birthDateFrom;

    @ApiModelProperty(value = "出生日期To，格式yyyy-MM-dd", position = 2)
    private Date birthDateTo;

    @ApiModelProperty(value = "出道日期From，格式yyyy-MM-dd", position = 3)
    private Date debutDateFrom;

    @ApiModelProperty(value = "出道日期To，格式yyyy-MM-dd", position = 4)
    private Date debutDateTo;

    @ApiModelProperty(value = "身高From (cm)",position = 5)
    private Integer heightFrom;

    @ApiModelProperty(value = "身高To (cm)", position = 6)
    private Integer heightTo;

    @ApiModelProperty(value = "体重From (kg)", position = 7)
    private Integer weightFrom;

    @ApiModelProperty(value = "体重To (kg)", position = 8)
    private Integer weightTo;

    @ApiModelProperty(value = "标星级别From", position = 9)
    private Integer starFrom;

    @ApiModelProperty(value = "标星级别To", position = 10)
    private Integer starTo;

    @ApiModelProperty(value = "标签 dicttype:001", position = 11)
    private String tags;

    @ApiModelProperty(value = "排序方式", position = 12)
    private String orderStr;
}
