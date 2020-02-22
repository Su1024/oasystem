package com.oasystem.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserInfo {
    /**
     * 员工id
     */
    private Integer id;

    /**
     * 员工编号
     */
    private String userCode;
    /**
     * 姓名
     */
    private String name;

    /**
     * 照片
     */
    private String photo;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0-女 1-男
     */
    private Integer gender;

    /**
     * 住址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 移动电话
     */
    private Integer mobilePhone;

    /**
     * 家庭电话
     */
    private Integer homePhone;

    /**
     * 入职时间
     */
    private Date entryTime;

    /**
     * 办公地点
     */
    private String officeLocation;

    /**
     * 办公电话
     */
    private Integer officePhone;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门id
     */
    private String deptName;

    /**
     * 民族
     */
    private String nation;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 户口所在地
     */
    private String registeredAddress;

    /**
     * 出生年月
     */
    private Date birth;

    /**
     * 政治面貌
     */
    private String politicalOutlook;

    /**
     * 身份证号码
     */
    private String idCode;

    /**
     * 婚姻状况 0-未婚 1-已婚
     */
    private Integer maritalStatus;

    private Integer roleId;

    /**
     * 备注
     */
    private String remarks;
}