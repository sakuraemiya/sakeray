package com.lesein.common.base.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class IdRequest extends BaseRequest{

    @NotNull(message = "ID" + notBlankMsg)
    @Min(value = 1, message = "ID必须大于0")
    private Long id;

    public Long getId() {
        return id;
    }

    public IdRequest setId(Long id) {
        this.id = id;
        return this;
    }
}
