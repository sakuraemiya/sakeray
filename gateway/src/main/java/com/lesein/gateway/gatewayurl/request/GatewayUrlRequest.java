package com.lesein.gateway.gatewayurl.request;

import com.lesein.common.base.request.BaseRequest;

/**
 * @author WangJie
 * @date 2023/4/11
 */
public class GatewayUrlRequest extends BaseRequest {
    /**
     * 项目id，一般为项目名称
     */
    private String uniqueId;
    /**
     * 地址
     */
    private String path;

    public String getUniqueId() {
        return uniqueId;
    }

    public GatewayUrlRequest setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public GatewayUrlRequest setPath(String path) {
        this.path = path;
        return this;
    }
}
