package com.oasystem.model;

import java.util.Date;

import lombok.Data;

@Data
public class DeptInfo {
    /**
     * 部门编号
     */
    private Integer id;
    /**
     * 部门编号
     */
    private String deptCode;
    /**
     * 部门经理编号
     */
    private Integer leadId;

    /**
     * 部门人数
     */
    private Integer peopleNumber;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remarks;

    private UserInfo userInfo;
}