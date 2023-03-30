package com.lesein.gateway.login.controller;

import com.lesein.gateway.login.request.LoginRequest;
import com.lesein.gateway.login.response.LoginResponse;
import com.lesein.gateway.login.service.LoginService;
import com.lesein.common.base.aop.RestConfiguration;
import com.lesein.common.base.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@RequestMapping("/auth")
@RestController
@RestConfiguration
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/authorization")
    public BaseResponse<LoginResponse> authorization(@RequestBody LoginRequest request){
        return BaseResponse.setSuccessResponse(loginService.authorization(request));
    }

    @PostMapping("/analysis")
    public BaseResponse<String> analysis(){
        loginService.analysis();
        return BaseResponse.setSuccessResponse();
    }
}