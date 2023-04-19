package com.lesein.gateway.exception.config;

import com.lesein.gateway.exception.service.GatewayExceptionService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @author WangJie
 * @date 2023/4/19
 */
@Configuration
public class GatewayExceptionConfig {
    /**
     * 自定义异常处理[@@]注册Bean时依赖的Bean，会从容器中直接获取，所以直接注入即可
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        GatewayExceptionService gatewayExceptionService = new GatewayExceptionService();
        gatewayExceptionService.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        gatewayExceptionService.setMessageWriters(serverCodecConfigurer.getWriters());
        gatewayExceptionService.setMessageReaders(serverCodecConfigurer.getReaders());
        return gatewayExceptionService;
    }
}
