package com.lesein.common.base.gateway.entity;

import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/3/31
 */
public class GatewayUrl implements Serializable {
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

    public GatewayUrl setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public GatewayUrl setPath(String path) {
        this.path = path;
        return this;
    }
}
