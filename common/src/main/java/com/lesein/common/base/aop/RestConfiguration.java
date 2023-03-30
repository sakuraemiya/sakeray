package com.lesein.common.base.aop;

import java.lang.annotation.*;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface RestConfiguration {
}
