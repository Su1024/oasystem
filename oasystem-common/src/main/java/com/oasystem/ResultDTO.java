package com.oasystem;

import java.io.Serializable;

/**
 * @ClassName ResultDTO
 * @Description
 * @Author suguoming
 * @Date 2020/2/7 11:33 下午
 */
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private T data;
    private String errorMsg;

    public ResultDTO() {
    }

    public ResultDTO(boolean success, T data, String errorMsg) {
        this.success = success;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static <T> ResultDTO<T> successResult(T obj) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccess(true);
        resultDTO.setData(obj);
        return resultDTO;
    }


    public static <T> ResultDTO<T> errorResult(String errorMsg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccess(false);
        resultDTO.setErrorMsg(errorMsg);
        return resultDTO;
    }

    public static <T> ResultDTO<T> errorResult(T obj, String errorMsg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccess(false);
        resultDTO.setData(obj);
        resultDTO.setErrorMsg(errorMsg);
        return resultDTO;
    }
}