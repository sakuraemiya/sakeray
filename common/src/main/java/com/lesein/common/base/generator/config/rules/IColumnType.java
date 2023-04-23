package com.lesein.common.base.generator.config.rules;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public interface IColumnType {
    /**
     *
     * 获取字段类型
     * @return 字段类型
     */
    String getType();

    /**
     *
     * 获取字段类型完整名
     * @return 字段类型完整名
     */
    String getPkg();
}
