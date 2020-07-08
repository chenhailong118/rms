package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.AuthUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Lee
 * @Description 用户传输对象
 * @Date 2020/6/22 21:33
 * @Version 1.0
 */
@Data
public class AuthUserDto extends AuthUser {
    public interface AddAuthUserGroup {}

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空", groups = {AddAuthUserGroup.class})
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空", groups = {AddAuthUserGroup.class})
    private String password;

    @ApiModelProperty(value = "确认密码")
    @NotNull(message = "确认密码不能为空", groups = {AddAuthUserGroup.class})
    private String comfirmPassword;

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "昵称不能为空", groups = {AddAuthUserGroup.class})
    private String nickname;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "性别：0->女；1->男")
    @Range(min = 0, max = 1, message = "性别只能为0和1: 0->女；1->男", groups = {AddAuthUserGroup.class})
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不正确", groups = {AddAuthUserGroup.class})
    @NotNull(message = "邮箱不能为空", groups = {AddAuthUserGroup.class})
    private String email;

    @ApiModelProperty(value = "联系方式")
    @NotNull(message = "联系方式不能为空", groups = {AddAuthUserGroup.class})
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;
}
