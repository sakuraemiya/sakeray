package com.lesein.gateway.url.api;

import com.lesein.common.base.response.BaseResponse;
import com.lesein.gateway.url.request.GatewayUrlRequest;

import java.util.List;

/**
 * @author WangJie
 * @date 2023/4/11
 */
public interface GatewayUrlApi {
    /**
     * 添加路由
     *
     * @param request
     * @return
     */
    BaseResponse<Void> addUrl(List<GatewayUrlRequest> request);
}
