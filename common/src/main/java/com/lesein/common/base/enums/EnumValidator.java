package com.lesein.common.base.enums;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author WangJie
 * @date 2023/4/24
 */
public class EnumValidator implements ConstraintValidator<EnumValidAnnotation, Object> {
    private static final Logger log = LoggerFactory.getLogger(EnumValidator.class);
    /**
     * 枚举类
     */
    Class<?>[] clzs;

    @Override
    public void initialize(EnumValidAnnotation constraintAnnotation) {
        clzs = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.debug("value=={}", value);
        /**
         * 如果枚举值为null表示不需要验证直接返回true
         */
        if (null == value) {
            return true;
        }
        if (clzs.length > 0) {
            try {
                for (Class<?> clz : clzs) {
                    if (clz.isEnum()) {
                        // 枚举类验证
                        Object[] objs = clz.getEnumConstants();
                        for (Object obj : objs) {
                            Class<?> enumClz = obj.getClass();
                            Field[] fields = enumClz.getDeclaredFields();
                            for (Field field : fields) {
                                // 访问私有成员属性开关
                                field.setAccessible(true);
                                EnumValidAnnotation enumValidAnnotation =
                                        field.getAnnotation(EnumValidAnnotation.class);
                                if (Objects.nonNull(enumValidAnnotation)) {
                                    // 获取成员属性的值
                                    Object enumValue = field.get(obj);
                                    if (Objects.equals(value, enumValue)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                log.error("异常信息是==={}", ExceptionUtils.getStackTrace(e));
            }
        } else {
            return true;
        }
        return false;
    }
}
