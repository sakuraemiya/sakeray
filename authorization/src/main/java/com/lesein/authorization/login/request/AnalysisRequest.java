package com.lesein.authorization.login.request;

import com.lesein.common.base.request.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author WangJie
 * @date 2023/4/14
 */
public class AnalysisRequest extends BaseRequest {
    @NotBlank(message = "token" + notBlankMsg)
    private String token;

    public String getToken() {
        return token;
    }

    public AnalysisRequest setToken(String token) {
        this.token = token;
        return this;
    }
}
