package com.lesein.gateway.token.request;

import com.lesein.common.base.request.BaseRequest;

/**
 * @author WangJie
 * @date 2023/4/14
 */
public class AnalysisRequest extends BaseRequest {
    private String token;

    public String getToken() {
        return token;
    }

    public AnalysisRequest setToken(String token) {
        this.token = token;
        return this;
    }
}
