package com.oasystem.model;

import java.util.Date;
import lombok.Data;

@Data
public class AttendsInfo {
    /**
    * 考勤编号
    */
    private Integer id;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 部门id
    */
    private Integer deptId;

    /**
    * 备注
    */
    private String remark;

    /**
    * 考勤类型 0-上班打卡 1-下班打卡 
    */
    private Integer type;

    /**
    * 考勤时间
    */
    private Date attendTime;

    /**
    * 0-正常 1-迟到 2-早退 3-旷工
    */
    private Integer status;

    private UserInfo userInfo;

    private DeptInfo deptInfo;
}