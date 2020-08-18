package com.still.rms.common.dto;

import com.still.rms.mbg.model.Actor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Author FishAndFlower
 * @Description 演员传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class ActorDto extends Actor {
    public interface AddActorGroup {}

    @ApiModelProperty(value = "性别")
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
