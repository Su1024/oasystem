package com.oasystem.model.dto;

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
@ApiModel(value = "考勤历史查询条件", description = "考勤历史查询条件")
public class AttendQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编号", name = "userCode", example = "1")
    private String userCode;

    @ApiModelProperty(value = "员工姓名", name = "userName", example = "1")
    private String name;

    @ApiModelProperty(value = "部门id", name = "deptId", example = "1")
    private Integer deptId;

    @ApiModelProperty(value = "查询开始时间", name = "beginDate", example = "1")
    private Date beginDate;

    @ApiModelProperty(value = "查询结束时间", name = "endDate", example = "1")
    private Date endDate;

}
