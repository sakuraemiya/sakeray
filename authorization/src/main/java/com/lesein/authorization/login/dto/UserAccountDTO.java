package com.lesein.authorization.login.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WangJie
 * @date 2023/4/24
 */
public class UserAccountDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String userMobile;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 状态1：启用，2禁用
     */
    private Integer status;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人id
     */
    private String updateUserId;

    /**
     * 更新人
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public UserAccountDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserAccountDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public UserAccountDTO setUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public UserAccountDTO setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserAccountDTO setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserAccountDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public UserAccountDTO setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public UserAccountDTO setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserAccountDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public UserAccountDTO setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public UserAccountDTO setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public UserAccountDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
