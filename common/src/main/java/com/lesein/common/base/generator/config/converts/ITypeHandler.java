package com.lesein.common.base.generator.config.converts;

import com.lesein.common.base.generator.constant.StringPool;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public interface ITypeHandler {

    /**
     * 是否启用转化
     * @param fieldName
     * @return
     */
    default boolean enable(String fieldName){
        return false;
    }

    /**
     * Mybatis字段类型转化
     * @param fieldName
     * @return
     */
    default String processTypeHandler(String fieldName){
        return StringPool.EMPTY;
    }
}
