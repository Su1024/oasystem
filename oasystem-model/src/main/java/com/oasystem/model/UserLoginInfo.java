package com.oasystem.model;

import lombok.Data;

@Data
public class UserLoginInfo {
    /**
    * 登录 ID
    */
    private Integer id;

    /**
    * 用户编号
    */
    private String userCode;

    /**
    * 用户密码
    */
    private String password;

    /**
    * 部门编号
    */
    private Integer deptId;

    /**
    * 角色编号
    */
    private Integer roleId;

    /**
    * 最近登录 IP 
    */
    private String ipAddress;

    /**
    * 登录状态
    */
    private Integer status;

    /**
    * 备注
    */
    private String remarks;
}