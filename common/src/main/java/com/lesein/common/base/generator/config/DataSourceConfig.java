package com.lesein.common.base.generator.config;

import com.lesein.common.base.generator.config.converts.ITypeConvert;
import com.lesein.common.base.generator.config.converts.MySqlTypeConvert;
import com.lesein.common.base.generator.config.type.DbType;
import com.lesein.common.base.generator.config.type.IDbQuery;
import com.lesein.common.base.generator.config.type.MySqlQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class DataSourceConfig {
    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 数据库信息查询
     */
    private IDbQuery dbQuery;

    /**
     * 驱动连接的URL
     */
    private String url;

    /**
     * 数据库名称
     */
    private String dataBaseName;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * 字段类型转换
     */
    private ITypeConvert typeConvert;

    public DataSourceConfig setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }

    public DataSourceConfig setDbQuery(IDbQuery dbQuery) {
        this.dbQuery = dbQuery;
        return this;
    }

    public DataSourceConfig setTypeConvert(ITypeConvert typeConvert) {
        this.typeConvert = typeConvert;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DataSourceConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public DataSourceConfig setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public DataSourceConfig setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceConfig setPassword(String password) {
        this.password = password;
        return this;
    }
    /**
     * 判断数据库类型
     * @return
     */
    public DbType getDbType() {
        return DbType.MYSQL;
    }

    /**
     * 获取数据库查询SQL
     * @return
     */
    public IDbQuery getDbQuery() {
        return new MySqlQuery();
    }

    /**
     * 获取字段类型转换器
     * @return
     */
    public ITypeConvert getTypeConvert() {
        return new MySqlTypeConvert();
    }

    /**
     * 创建数据库连接对象
     *
     * @return Connection
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
