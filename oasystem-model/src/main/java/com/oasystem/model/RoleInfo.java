package com.oasystem.model;

import lombok.Data;

@Data
public class RoleInfo {
    /**
    * 角色id
    */
    private Integer id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色权限值
    */
    private Integer permissionValue;

    /**
    * 角色权限描述
    */
    private String desc;

    /**
    * 备注
    */
    private String remarks;
}