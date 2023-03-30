package com.lesein.common.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesein.common.base.exception.BusinessValidateException;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JacksonUtil() {
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            throw new BusinessValidateException(var2);
        }
    }
}
