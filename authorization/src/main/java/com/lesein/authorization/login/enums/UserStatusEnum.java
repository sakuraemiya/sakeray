package com.lesein.authorization.login.enums;

import com.lesein.common.base.enums.EnumCodeName;
import com.lesein.common.base.enums.EnumValidAnnotation;

/**
 * @author WangJie
 * @date 2023/4/24
 */
public enum  UserStatusEnum {
    /**
     * 人员账号状态
     */
    启用(1, "启用"),
    禁用(2, "禁用");

    @EnumValidAnnotation
    private Integer code;

    @EnumCodeName
    private String name;

    UserStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
