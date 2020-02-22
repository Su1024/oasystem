package com.oasystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName fileQueryDTO
 * @Description
 * @Author suguoming
 * @Date 2020/2/8 12:23 上午
 */
@Data
@ApiModel(value = "用户查询条件", description = "用户查询条件")
public class UserQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", name = "userCode", example = "1")
    private String userCode;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "name", example = "张三")
    private String name;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间", name = "entryTime", example = "2020-02-07 21：09：00", hidden = true)
    private Date entryTime;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", name = "deptId", example = "1")
    private Integer deptId;

}
