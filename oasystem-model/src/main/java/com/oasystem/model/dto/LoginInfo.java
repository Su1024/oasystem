package com.oasystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginInfo
 * @Description
 * @Author suguoming
 * @Date 2020/2/17 1:30 上午
 */
@Data
@ApiModel(value = "登录对象", description = "登录对象")
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", name = "userId", example = "1")
    private String userId;

    @ApiModelProperty(value = "用户账号", name = "username", example = "1")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "1")
    private String password;
}
