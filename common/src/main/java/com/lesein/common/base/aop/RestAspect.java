package com.lesein.common.base.aop;

import com.lesein.common.base.exception.BusinessValidateException;
import com.lesein.common.base.response.BaseResponse;
import com.lesein.common.base.util.JacksonUtil;
import com.lesein.common.base.util.JdkStreamUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Aspect
public class RestAspect {
    private static final Logger log = LoggerFactory.getLogger(RestAspect.class);

    @Pointcut("@within(com.lesein.common.base.aop.RestConfiguration)|| @annotation(com.lesein.common.base.aop.RestConfiguration)")
    public void requestAndResponseAspect() {
    }

    @Around("requestAndResponseAspect()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            for (Object arg : args) {
                Set<ConstraintViolation<Object>> violations = Validation.buildDefaultValidatorFactory()
                        .getValidator()
                        .validate(arg);
                if (!violations.isEmpty()) {
                    String errorMessage = String.join(",", JdkStreamUtil.fetchList(violations, ConstraintViolation::getMessage));
                    throw new BusinessValidateException(errorMessage);
                }
            }
            return proceedingJoinPoint.proceed();
        }catch (BusinessValidateException e ){
            return new BaseResponse(false,500,null,e.getMessage());
        } catch (Throwable e){
            log.error("doAround系统发生异常，异常类和方法是=={}，异常信息是=={},异常参数, 参数类型和参数名称和参数值=={}", getClassMethod(proceedingJoinPoint), e.getMessage(),getParametersLog(proceedingJoinPoint),e);
            return new BaseResponse(false,500,null,"系统异常");
        }
    }

    private String getClassMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        return signature.getDeclaringTypeName() + "." + signature.getName();
    }
    private String getParametersLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Class[] types = methodSignature.getParameterTypes();

        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                stringBuilder.append("参数类型：" + types[i].getName() + "参数名称：" + parameterNames[i] + ",参数值：" + JacksonUtil.toJson(args[i]) + "。");
            }
        }
        return stringBuilder.toString();
    }
}
