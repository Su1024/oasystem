package com.oasystem.model;

import java.util.Date;
import lombok.Data;

@Data
public class SysLog {
    /**
    * 日志Id
    */
    private Integer id;

    /**
    * 操作模块
    */
    private String operModel;

    /**
    * 操作类型
    */
    private String operType;

    /**
    * 操作描述
    */
    private String operDesc;

    /**
    * 操作人id
    */
    private Integer operUserId;

    /**
    * 操作人姓名
    */
    private String operUserName;

    /**
    * 操作人ip
    */
    private String operUserIp;

    private Date operTime;

    private Integer operFileId;
}