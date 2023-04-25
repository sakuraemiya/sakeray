package com.lesein.common.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesein.common.base.exception.BusinessValidateException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public JacksonUtil() {
    }

    /**
     * 将对象序列化成json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            throw new BusinessValidateException(var2);
        }
    }

    /**
     * 将对象json反序列化成对象
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        if (json == null) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, classOfT);
            } catch (IOException var3) {
                throw new BusinessValidateException(var3);
            }
        }
    }

    /**
     * 将json字符串转对象，支持范型类
     * @param value
     * @return
     */
    public static <T> T jsonToObj(String value, TypeReference<T> referenceType){
        try {
            if(StringUtils.isNotBlank(value)){
                return objectMapper.readValue(value, referenceType);
            }
        } catch (Exception e){
            throw new BusinessValidateException(e);
        }
        return null;
    }

    /**
     * 将字节流转对象，支持范型类
     * @param value
     * @return
     */
    public static <T> T byteToObj(byte[] value, TypeReference<T> referenceType){
        try {
            if(Objects.nonNull(value)){
                return objectMapper.readValue(value, referenceType);
            }
        } catch (Exception e){
            throw new BusinessValidateException(e);
        }
        return null;
    }
}
