package com.lesein.common.base.generator.config.type;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public interface IDbQuery {

    /**
     * 数据库类型
     */
    DbType dbType();

    /**
     * 数据库查询 SQL
     * @return
     */
    String dataBase();

    /**
     * 表信息查询 SQL
     */
    String tablesSql();


    /**
     * 表字段信息查询 SQL
     */
    String tableFieldsSql();


    /**
     * 表名称
     */
    String tableName();


    /**
     * 表注释
     */
    String tableComment();


    /**
     * 字段名称
     */
    String fieldName();


    /**
     * 字段类型
     */
    String fieldType();

    /**
     * 字段长度
     * @return
     */
    String fieldLength();

    /**
     * 字段注释
     */
    String fieldComment();

    /**
     * 主键字段
     */
    String fieldKey();
}
