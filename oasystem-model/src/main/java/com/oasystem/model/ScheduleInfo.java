package com.oasystem.model;

import java.util.Date;
import lombok.Data;

@Data
public class ScheduleInfo {
    /**
    * 编号
    */
    private Integer id;

    /**
    * 起始时间
    */
    private Date startTime;

    /**
    * 结束时间
    */
    private Date endTime;

    /**
    * 内容
    */
    private String content;

    /**
    * 地点
    */
    private String address;

    /**
    * 说明
    */
    private String remark;

    /**
    * 是否公开 0不公开 1公开
    */
    private Integer isShow;

    /**
    * 日程创建时间
    */
    private Date createTime;

    /**
    * 日程修改时间
    */
    private Date updateTime;

    /**
    * 是否删除 0不删除 1删除
    */
    private Integer isDelete;
}