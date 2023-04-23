package com.lesein.common.base.generator.config.type;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class MySqlQuery implements IDbQuery {
    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }

    @Override
    public String dataBase() {
        return "select database()";
    }


    @Override
    public String tablesSql() {
        return "show table status";
    }


    @Override
    public String tableFieldsSql() {
        return "SELECT * FROM information_schema.columns where table_name = '%s' and table_schema = '%s'";
    }


    @Override
    public String tableName() {
        return "NAME";
    }


    @Override
    public String tableComment() {
        return "COMMENT";
    }


    @Override
    public String fieldName() {
        return "COLUMN_NAME";
    }


    @Override
    public String fieldType() {
        return "DATA_TYPE";
    }

    @Override
    public String fieldLength() {
        return "CHARACTER_MAXIMUM_LENGTH";
    }


    @Override
    public String fieldComment() {
        return "COLUMN_COMMENT";
    }

    @Override
    public String fieldKey() {
        return "COLUMN_KEY";
    }
}
