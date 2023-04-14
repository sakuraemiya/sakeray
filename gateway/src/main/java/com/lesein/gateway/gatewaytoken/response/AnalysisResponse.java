package com.lesein.gateway.gatewaytoken.response;

import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/4/14
 */
public class AnalysisResponse implements Serializable {
    private Long userId;
    private String userName;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public AnalysisResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AnalysisResponse setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AnalysisResponse setPassword(String password) {
        this.password = password;
        return this;
    }
}
