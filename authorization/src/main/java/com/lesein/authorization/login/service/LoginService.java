package com.lesein.authorization.login.service;

import com.lesein.authorization.login.request.LoginRequest;
import com.lesein.authorization.login.response.LoginResponse;
import com.lesein.common.base.util.ParamValidatorUtil;
import org.springframework.stereotype.Component;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Component
public class LoginService {

    /**
     * 登录
     * @param request
     * @return
     */
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        ParamValidatorUtil.checkNotBlank(request.getUserName(), "用户名不能为空");
        ParamValidatorUtil.checkNotBlank(request.getPassword(), "密码不能为空");
        return response.setToken(request.getUserName() + "-" + request.getPassword());
    }


    public void analysis(){

    }
}
