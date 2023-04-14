package com.lesein.authorization.login.api;

import com.lesein.authorization.login.request.AnalysisRequest;
import com.lesein.authorization.login.request.LoginRequest;
import com.lesein.authorization.login.response.AnalysisResponse;
import com.lesein.authorization.login.response.LoginResponse;
import com.lesein.authorization.login.service.LoginService;
import com.lesein.common.base.aop.RestConfiguration;
import com.lesein.common.base.request.BaseRequest;
import com.lesein.common.base.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author WangJie
 * @date 2023/3/29
 */
public interface LoginApi {
    /**
     * 人员登录授权
     * @param request
     * @return
     */
    BaseResponse<LoginResponse> authorization(LoginRequest request);

    /**
     * token解析
     * @param request
     * @return
     */
    BaseResponse<AnalysisResponse> analysis(AnalysisRequest request);

    /**
     * 获取人员信息
     * @param request
     * @return
     */
    BaseResponse<String> getUserInfo(BaseRequest request);

}
