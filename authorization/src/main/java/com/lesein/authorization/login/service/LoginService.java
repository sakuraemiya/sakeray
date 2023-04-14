package com.lesein.authorization.login.service;

import com.lesein.authorization.login.request.AnalysisRequest;
import com.lesein.authorization.login.request.LoginRequest;
import com.lesein.authorization.login.response.AnalysisResponse;
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
    public LoginResponse authorization(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        ParamValidatorUtil.checkNotBlank(request.getUserName(), "用户名不能为空");
        ParamValidatorUtil.checkNotBlank(request.getPassword(), "密码不能为空");
        return response.setToken(request.getUserName() + "-" + request.getPassword());
    }

    /**
     * token解析
     * @param request
     */
    public AnalysisResponse analysis(AnalysisRequest request){
        AnalysisResponse response=new AnalysisResponse();
        ParamValidatorUtil.checkNotBlank(request.getToken(), "token不能为空");
        return response.setUserId(1L).setUserName(request.getToken().split("-")[0]).setPassword(request.getToken().split("-")[1]);
    }
}
