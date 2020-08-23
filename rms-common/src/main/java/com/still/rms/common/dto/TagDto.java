package com.still.rms.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.still.rms.mbg.model.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author FishAndFlower
 * @Description 标签传输对象
 * @Date 2020/8/19 11:17
 * @Version 1.0
 */
@Data
public class TagDto extends Tag {
    public interface AddTagGroup{}

    @ApiModelProperty(value = "父级ID")
    @NotNull(message = "父级ID不能为空", groups = {AddTagGroup.class})
    private Long parentId;

    @ApiModelProperty(value = "标签名称")
    @NotNull(message = "标签名称不能为空", groups = {AddTagGroup.class})
    private String name;
}
