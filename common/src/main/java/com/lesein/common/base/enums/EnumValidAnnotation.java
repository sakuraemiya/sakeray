package com.lesein.common.base.enums;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author WangJie
 * @date 2023/4/24
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
@Documented
public @interface EnumValidAnnotation {


    /**
     * 提示消息
     *
     * @return
     */
    String message() default "枚举值不存在！";

    /**
     * 对应的枚举类
     *
     * @return
     */
    Class<?>[] target() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}