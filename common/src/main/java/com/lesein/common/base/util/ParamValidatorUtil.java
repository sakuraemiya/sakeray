package com.lesein.common.base.util;

import com.lesein.common.base.exception.BusinessValidateException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author WangJie
 * @date 2023/3/29
 * 参数校验器
 */
public class ParamValidatorUtil {

    private ParamValidatorUtil() {
    }

    /**
     * 校验参数是否为空
     * @param obj
     * @param message
     */
    public static void checkNotNull(Object obj, Object message) {
        if (obj == null) {
            throw new BusinessValidateException(String.valueOf(message));
        }
    }

    /**
     * 校验字符串是否为空
     * @param string
     * @param message
     */
    public static void checkNotBlank(String string, Object message) {
        if(StringUtils.isBlank(string)){
            throw new BusinessValidateException(String.valueOf(message));
        }
    }
}
