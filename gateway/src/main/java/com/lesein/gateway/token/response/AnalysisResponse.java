package com.lesein.gateway.token.response;

import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/4/14
 */
public class AnalysisResponse implements Serializable {
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

    public Long getId() {
        return id;
    }

    public AnalysisResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AnalysisResponse setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public AnalysisResponse setUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }
}
