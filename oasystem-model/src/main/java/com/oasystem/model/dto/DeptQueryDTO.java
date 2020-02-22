package com.oasystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DeptQueryDTO
 * @Description
 * @Author suguoming
 * @Date 2020/2/16 9:22 下午
 */
@Data
@ApiModel(value = "部门查询条件", description = "部门查询条件")
public class DeptQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "部门编号", name = "deptCode", example = "1")
    private String deptCode;

    @ApiModelProperty(value = "部门名称", name = "name", example = "1")
    private String name;


}
