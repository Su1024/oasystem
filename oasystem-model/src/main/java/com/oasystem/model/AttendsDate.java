package com.oasystem.model;

import java.util.Date;
import lombok.Data;

@Data
public class AttendsDate {
    /**
    * 日期编号
    */
    private Integer id;

    /**
    * 年份
    */
    private String attendYear;

    /**
    * 日期
    */
    private String attendDate;

    /**
    * 是否工作日 0-工作日 1-假期
    */
    private Integer isWork;

    /**
    * 上班时间
    */
    private String workBeginTime;

    /**
    * 下班时间
    */
    private String workEndTime;

    /**
    * 备注
    */
    private String remark;

    /**
    * 是否有效 0-有效 1-无效
    */
    private Integer isDelete;
}