package com.lesein.authorization.login.controller;

import com.lesein.authorization.login.request.LoginRequest;
import com.lesein.authorization.login.response.LoginResponse;
import com.lesein.authorization.login.service.LoginService;
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

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request){
        return BaseResponse.setSuccessResponse(loginService.login(request));
    }

    @PostMapping("/analysis")
    public BaseResponse<String> analysis(){
        loginService.analysis();
        return BaseResponse.setSuccessResponse();
    }
}
