package com.lesein.authorization.login.request;

import com.lesein.common.base.request.BaseRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public class LoginRequest extends BaseRequest {
    @NotBlank(message = "账号" + notBlankMsg)
    private String accountName;
    @NotBlank(message = "密码" + notBlankMsg)
    private String password;

    public String getAccountName() {
        return accountName;
    }

    public LoginRequest setAccountName(String accountName) {
        this.accountName = accountName;
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
