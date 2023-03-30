package com.lesein.common.base.exception;

/**
 * @author WangJie
 * @date 2023/3/29
 * 自定义异常类
 */
public class BusinessValidateException extends RuntimeException{

    public BusinessValidateException(String message) {
        super(message);
    }
    public BusinessValidateException(Throwable ex) {
        super(ex);
    }
}
