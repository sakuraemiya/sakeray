package com.lesein.authorization.login.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 用户账号密码表
 * </p>
 *
 * @author WangJie
 * @since 2023-04-24
 */
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	

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
		return this.id;
	}

	public UserAccount setId(Long id) {
		this.id = id;
		return this;
	}
	public String getUserName() {
		return this.userName;
	}

	public UserAccount setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getUserMobile() {
		return this.userMobile;
	}

	public UserAccount setUserMobile(String userMobile) {
		this.userMobile = userMobile;
		return this;
	}
	public String getUserAccount() {
		return this.userAccount;
	}

	public UserAccount setUserAccount(String userAccount) {
		this.userAccount = userAccount;
		return this;
	}
	public String getUserPassword() {
		return this.userPassword;
	}

	public UserAccount setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}
	public Integer getStatus() {
		return this.status;
	}

	public UserAccount setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public String getCreateUserId() {
		return this.createUserId;
	}

	public UserAccount setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
		return this;
	}
	public String getCreateUserName() {
		return this.createUserName;
	}

	public UserAccount setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
		return this;
	}
	public Date getCreateTime() {
		return this.createTime;
	}

	public UserAccount setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public UserAccount setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
		return this;
	}
	public String getUpdateUserName() {
		return this.updateUserName;
	}

	public UserAccount setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
		return this;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public UserAccount setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
}
