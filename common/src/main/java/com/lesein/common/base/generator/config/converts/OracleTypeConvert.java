package com.lesein.common.base.generator.config.converts;


import com.lesein.common.base.generator.config.rules.DbColumnType;
import com.lesein.common.base.generator.config.rules.IColumnType;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class OracleTypeConvert implements ITypeConvert {

    @Override
    public String convertJdbcType(String fieldName, String fieldType) {
        String dbFieldType = fieldType.toUpperCase();
        if (StringUtils.equals(dbFieldType, "DATETIME")) {
            return "TIMESTAMP";
        }
        if (StringUtils.contains(dbFieldType, "TIMESTAMP")) {
            return "TIMESTAMP";
        }
        if (StringUtils.contains(dbFieldType, "VARCHAR2")) {
            return "VARCHAR";
        }
        if (StringUtils.endsWithIgnoreCase(dbFieldType, "TEXT")) {
            return "VARCHAR";
        }
        if (StringUtils.equals(dbFieldType, "INTEGER")
                || StringUtils.equals(dbFieldType, "INT")
                || StringUtils.equals(dbFieldType, "TINYINT")
                || StringUtils.equals(dbFieldType, "SMALLINT")
                || StringUtils.equals(dbFieldType, "MEDIUMINT")) {
            return "INTEGER";
        }
        return dbFieldType;
    }

    @Override
    public IColumnType convertPropertyType(String fieldName, String fieldType) {
        IColumnType customerType = customerType(fieldName, fieldType);
        if(Objects.nonNull(customerType)){
            return customerType;
        }
        String t = fieldType.toLowerCase();
        if (t.contains("char")) {
            return DbColumnType.STRING;
        } else if (t.contains("date") || t.contains("timestamp")) {
            return DbColumnType.DATE;
        } else if (t.contains("number")) {
            if (t.matches("number\\(+\\d\\)")) {
                return DbColumnType.INTEGER;
            } else if (t.matches("number\\(+\\d{2}+\\)")) {
                return DbColumnType.LONG;
            }
            return DbColumnType.DOUBLE;
        } else if (t.contains("float")) {
            return DbColumnType.FLOAT;
        } else if (t.contains("clob")) {
            return DbColumnType.CLOB;
        } else if (t.contains("blob")) {
            return DbColumnType.BLOB;
        } else if (t.contains("binary")) {
            return DbColumnType.BYTE_ARRAY;
        } else if (t.contains("raw")) {
            return DbColumnType.BYTE_ARRAY;
        }
        return DbColumnType.STRING;
    }
}
