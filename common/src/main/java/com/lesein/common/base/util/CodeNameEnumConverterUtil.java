package com.lesein.common.base.util;

import com.lesein.common.base.enums.EnumCodeName;
import com.lesein.common.base.enums.EnumValidAnnotation;
import com.lesein.common.base.exception.BusinessValidateException;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author WangJie
 * @date 2023/4/24
 */
public class CodeNameEnumConverterUtil {
    private static final Logger log = LoggerFactory.getLogger(CodeNameEnumConverterUtil.class);

    public static <T extends Enum<T>> String code2Name(Class<T> enumClazz, Object code) {
        try {
            final Pair<Field, Field> codeNameField = getCodeNameField(enumClazz);
            Field codeField = codeNameField.getLeft();
            Field nameField = codeNameField.getRight();
            if (Objects.isNull(codeField)) {
                throw new BusinessValidateException(enumClazz.getName() + "：EnumValidAnnotation注解字段不存在");
            }
            if (Objects.isNull(nameField)) {
                throw new BusinessValidateException(enumClazz.getName() + "：EnumCodeName注解字段不存在");
            }
            for (Object enumItem : enumClazz.getEnumConstants()) {
                if (Objects.equals(codeField.get(enumItem), code)) {
                    return String.valueOf(nameField.get(enumItem));
                }
            }
            return null;
        } catch (IllegalAccessException e) {
            log.error("枚举转换失败:{}", e.getMessage(), e);
            return null;
        }
    }

    public static <T extends Enum<T>> T code2Enum(Class<T> enumClazz, Object code) {
        final Pair<Field, Field> codeNameField = getCodeNameField(enumClazz);
        Field codeField = codeNameField.getLeft();
        if (Objects.isNull(codeField)) {
            throw new BusinessValidateException(enumClazz.getName() + "：EnumValidAnnotation注解字段不存在");
        }
        try {
            for (Object enumItem : enumClazz.getEnumConstants()) {
                if (Objects.equals(codeField.get(enumItem), code)) {
                    return ((T) enumItem);
                }
            }
        } catch (IllegalAccessException e) {
            log.error("枚举转换失败:{}", e.getMessage(), e);
            return null;
        }
        return null;
    }

    private static <T extends Enum<T>> Pair<Field, Field> getCodeNameField(Class<T> enumClazz) {
        final Field[] fields =
                enumClazz.getDeclaredFields();
        Field codeField = null;
        Field nameField = null;
        for (Field field : fields) {
            if (Objects.isNull(codeField) && Objects.nonNull(field.getAnnotation(EnumValidAnnotation.class))) {
                field.setAccessible(true);
                codeField = field;
            } else if (Objects.isNull(nameField) && Objects.nonNull(field.getAnnotation(EnumCodeName.class))) {
                field.setAccessible(true);
                nameField = field;
            }
        }
        return Pair.of(codeField, nameField);
    }
}
