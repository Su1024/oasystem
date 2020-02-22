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
@ApiModel(value = "查询文件条件", description = "查询文件条件")
public class FileQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文档属性
     */
    @ApiModelProperty(value = "文档属性", name = "type", example = "1")
    private String type;

    /**
     * 文档创建者
     */
    @ApiModelProperty(value = "文档创建者", name = "createUser", example = "张三")
    private String createUser;

    /**
     * 文档创建时间
     */
    @ApiModelProperty(value = "文档创建时间", name = "createTime", example = "2020-02-07 21：09：00", hidden = true)
    private Date createTime;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", name = "groupId", example = "1")
    private Integer groupId;

    private Integer pid;

    private Integer id;

    private String path;

    private String key;

}
