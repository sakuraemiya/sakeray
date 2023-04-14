package com.lesein.authorization.login.impl;

import com.lesein.authorization.login.api.LoginApi;
import com.lesein.authorization.login.request.AnalysisRequest;
import com.lesein.authorization.login.request.LoginRequest;
import com.lesein.authorization.login.response.AnalysisResponse;
import com.lesein.authorization.login.response.LoginResponse;
import com.lesein.authorization.login.service.LoginService;
import com.lesein.common.base.aop.RestConfiguration;
import com.lesein.common.base.request.BaseRequest;
import com.lesein.common.base.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangJie
 * @date 2023/4/11
 */
@RequestMapping("/auth")
@RestController
@RestConfiguration
public class LoginApiImpl implements LoginApi {

    @Resource
    private LoginService loginService;

    @Override
    @PostMapping("/authorization")
    public BaseResponse<LoginResponse> authorization(@RequestBody LoginRequest request){
        return BaseResponse.setSuccessResponse(loginService.authorization(request));
    }

    @Override
    @PostMapping("/analysis")
    public BaseResponse<AnalysisResponse> analysis(@RequestBody AnalysisRequest request){
        return BaseResponse.setSuccessResponse(loginService.analysis(request));
    }

    @Override
    @PostMapping("/getUserInfo")
    public BaseResponse<String> getUserInfo(@RequestBody BaseRequest request) {
        return BaseResponse.setSuccessResponse("hello: "+ request.getUserId());
    }
}
