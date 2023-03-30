package com.lesein.gateway.login.request;

import com.lesein.common.base.request.BaseRequest;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public class LoginRequest extends BaseRequest {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public LoginRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
