package com.oasystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName WorkDate
 * @Description
 * @Author suguoming
 * @Date 2020/2/18 11:25 下午
 */
@Data
@ApiModel(value = "设定工作日条件", description = "设定工作日条件")
public class WorkDate implements Serializable {

    @ApiModelProperty(value = "日期", name = "currentDate", example = "1")
    private String currentDate;

    @ApiModelProperty(value = "是否工作日", name = "isWork", example = "1")
    private Integer isWork;
}
