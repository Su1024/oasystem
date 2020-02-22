package com.oasystem.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "文件对象", description = "文件对象")
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文档编号
     */
    @ApiModelProperty(value = "文档编号", name = "id", example = "1", hidden = true)
    private Integer id;

    /**
     * 文档属性
     */
    @ApiModelProperty(value = "文档属性", name = "type", example = "1")
    private Integer type;
    /**
     * 文档位置
     */
    @ApiModelProperty(value = "文档位置", name = "location", example = "test位置")
    private String location;

    /**
     * 文档名称
     */
    @ApiModelProperty(value = "文档名称", name = "name", example = "这是一个很重要的文件")
    private String name;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", name = "groupId", example = "1")
    private Integer groupId;

    /**
     * 文档创建者
     */
    @ApiModelProperty(value = "文档创建者", name = "createUser", example = "张三")
    private Integer createUser;

    /**
     * 文档创建时间
     */
    @ApiModelProperty(value = "文档创建时间", name = "createTime", example = "2020-02-07 21：09：00", hidden = true)
    private Date createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remarks", example = "备注信息")
    private String remarks;

    /**
     * 文档更新时间
     */
    @ApiModelProperty(value = "文档更新时间", name = "updateTime", example = "2020-02-07 21：09：00", hidden = true)
    private Date updateTime;

    /**
     * 是否删除 0-未删除 1-删除
     */
    @ApiModelProperty(value = "是否删除", name = "isDelete", example = "0", hidden = true)
    private Integer isDelete;

    private Integer pid;

    private Integer size;
}