package com.oasystem.model.vo;

import com.oasystem.model.FileInfo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @ClassName FileInfoVo
 * @Description
 * @Author suguoming
 * @Date 2020/2/19 6:10 下午
 */
public class FileInfoVo extends FileInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String suffixName;

    private String userName;

    private String deptName;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSuffixName() {
        Integer type = this.getType();
        switch (type) {
            case 2:
                return "jpg";
            case 3:
                return "mp4";
        }

        return suffixName;
    }

    public void setSuffixName(String suffixName) {

        this.suffixName = suffixName;
    }
}
