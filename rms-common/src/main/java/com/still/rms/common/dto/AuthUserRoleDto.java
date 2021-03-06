package com.still.rms.common.dto;

import com.still.rms.mbg.model.AuthUserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 用户角色关系传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthUserRoleDto extends AuthUserRole {

    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(value = "角色ID数组")
    @NotEmpty(message = "角色ID不能为空")
    private List<Long> roleIds;
}
