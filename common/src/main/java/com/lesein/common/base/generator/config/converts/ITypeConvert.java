package com.lesein.common.base.generator.config.converts;


import com.lesein.common.base.generator.config.rules.IColumnType;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public interface ITypeConvert {


    /**
     * 执行数据库类型转换
     * @param fieldName
     * @param fieldType
     * @return
     */
    String convertJdbcType(String fieldName, String fieldType);

    /**
     * 执行实体类型转换
     * @param fieldName
     * @param fieldType
     * @return
     */
    IColumnType convertPropertyType(String fieldName, String fieldType);

    /**
     * 自定义列名
     * @param fieldName
     * @return
     */
    default String customerName(String fieldName){
        return fieldName;
    };

    /**
     * 支持自定义类型转化
     * @param fieldName
     * @param fieldType
     * @return
     */
    default IColumnType customerType(String fieldName,String fieldType){
        return null;
    };
}
