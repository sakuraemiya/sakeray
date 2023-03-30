package com.lesein.gateway.login.response;

import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public class LoginResponse implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
