package com.lesein.common.base.aop;

import com.lesein.common.base.exception.BusinessValidateException;
import com.lesein.common.base.response.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Aspect
public class RestAspect {

    @Pointcut("@within(com.lesein.common.base.aop.RestConfiguration)|| @annotation(com.lesein.common.base.aop.RestConfiguration)")
    public void requestAndResponseAspect() {
    }

    @Around("requestAndResponseAspect()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed();
        }catch (BusinessValidateException e){
            return new BaseResponse(false,500,null,e.getMessage());
        }catch (Throwable e){
            return new BaseResponse(false,500,null,"系统异常");
        }
    }
}
