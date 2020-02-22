package com.oasystem.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "附件对象", description = "附件对象")
public class AttachmentInfo {
    /**
     * 附件编号
     */
    @ApiModelProperty(value = "附件编号", name = "id", example = "1", hidden = true)
    private Integer id;

    /**
     * 附件所属文档
     */
    @ApiModelProperty(value = "附件所属文档", name = "fileId", example = "1")
    private Integer fileId;

    /**
     * 附件位置
     */
    @ApiModelProperty(value = "附件位置", name = "location", example = "测试位置")
    private String location;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称", name = "name", example = "这是一个附件")
    private String name;

    /**
     * 附件属性
     */
    @ApiModelProperty(value = "附件属性", name = "type", example = "附件属性")
    private String type;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", name = "createUesr", example = "李四")
    private Integer createUesr;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "2020-02-01 01:00:00", hidden = true)
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", name = "updateTime", example = "2020-02-01 01:00:00", hidden = true)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remarks", example = "备注信息")
    private String remarks;

    /**
     * 是否删除 0-未删除 1-删除
     */
    @ApiModelProperty(value = "是否删除", name = "isDelete", example = "0", hidden = true)
    private Integer isDelete;

    @ApiModelProperty(value = "是否删除", name = "isDelete", example = "0", hidden = true)
    private Integer size;
}