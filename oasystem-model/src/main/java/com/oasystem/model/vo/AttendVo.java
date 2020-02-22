package com.oasystem.model.vo;

import com.oasystem.model.DeptInfo;
import com.oasystem.model.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AttendQueryDTO
 * @Description
 * @Author suguoming
 * @Date 2020/2/17 6:36 下午
 */
@Data
@ApiModel(value = "考勤统计查询结果", description = "考勤统计查询结果")
public class AttendVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer deptId;
    private Integer userCode;
    private String attendTime;
    private Integer late;
    private Integer early;
    private Integer absenteeism;

}
