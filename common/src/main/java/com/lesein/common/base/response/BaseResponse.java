package com.lesein.common.base.response;

import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/3/29
 * 基础出参类
 */
public class BaseResponse<T> implements Serializable {
    private boolean success;
    private Integer code;
    private T data;


    public BaseResponse(boolean success, Integer code, T data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }

    public static <T> BaseResponse <T> setSuccessResponse(T data) {
        return new BaseResponse(true, 200, data);
    }

    public static <T> BaseResponse <T> setSuccessResponse() {
        return new BaseResponse(true, 200, null);
    }


    public boolean isSuccess() {
        return success;
    }

    public BaseResponse<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public BaseResponse<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
