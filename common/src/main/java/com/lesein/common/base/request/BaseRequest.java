package com.lesein.common.base.request;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/3/29
 * 基础入参类
 */
public class BaseRequest implements Serializable {
    public static final String notBlankMsg = "不能为空！";

    /**
     * 登陆人id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 页数
     */
    @Min(value = 0, message = "页码必须大于等于0的整数")
    private Integer page;
    /**
     * 每页大小
     */
    @Min(value = 0, message = "pageSize必须大于等于0的整数")
    private Integer pageSize;
    /**
     * 是否开启分页
     */
    private boolean pageEnable = true;
    /**
     * 分页开始页数
     */
    private Integer start;
    /**
     * 每页大小
     */
    private Integer limit;

    public Long getUserId() {
        return userId;
    }

    public BaseRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public BaseRequest setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BaseRequest setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public boolean isPageEnable() {
        return pageEnable;
    }

    public BaseRequest setPageEnable(boolean pageEnable) {
        this.pageEnable = pageEnable;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public BaseRequest setStart(Integer start) {
        this.start = start;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public BaseRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public BaseRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public BaseRequest setUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }
}
