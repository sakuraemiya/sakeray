package com.lesein.gateway.gatewaytoken.service;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author WangJie
 * @date 2023/4/11
 */
@Component
public class GatewayTokenService implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        PathContainer pathContainer = request.getPath().pathWithinApplication();
        // 添加gatewayKey，防止下游接口直接被访问
        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header("gatewayKey", "key");
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
