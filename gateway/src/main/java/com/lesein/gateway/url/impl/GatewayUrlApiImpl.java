package com.lesein.gateway.url.impl;

import com.lesein.common.base.aop.RestConfiguration;
import com.lesein.common.base.response.BaseResponse;
import com.lesein.gateway.url.api.GatewayUrlApi;
import com.lesein.gateway.url.request.GatewayUrlRequest;
import com.lesein.gateway.url.service.GatewayUrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WangJie
 * @date 2023/4/11
 */
@RequestMapping("/gatewayUrlApi")
@RestController
@RestConfiguration
public class GatewayUrlApiImpl implements GatewayUrlApi {

    @Resource
    private GatewayUrlService gatewayUrlService;

    @Override
    @PostMapping("/addUrl")
    public BaseResponse<Void> addUrl(@RequestBody List<GatewayUrlRequest> request){
        gatewayUrlService.addUrl(request);
        return BaseResponse.setSuccessResponse();
    }
}
