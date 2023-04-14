package com.lesein.common.base.request;

import java.io.Serializable;

/**
 * @author WangJie
 * @date 2023/3/29
 * 基础入参类
 */
public class BaseRequest implements Serializable {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public BaseRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
