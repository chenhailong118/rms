package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.Actor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Author Lee
 * @Description 演员传输对象
 * @Date 2020/6/21 15:55
 * @Version 1.0
 */
@Data
public class ActorDto extends Actor {
    public interface AddActorGroup {}

    @ApiModelProperty(value = "性别，0->女；1->男")
    @Range(min = 0, max = 1, message = "性别只能为0和1: 0->女；1->男", groups = AddActorGroup.class)
    private Integer sex;

    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空", groups = AddActorGroup.class)
    private String name;

    @ApiModelProperty(value = "出生日期")
    @Past(message = "出生日期必须为过去时间")
    private Date birthdate;

    @ApiModelProperty(value = "出道日期")
    @Past(message = "出道日期必须为过去时间")
    private Date debutdate;
}
