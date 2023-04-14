package com.lesein.gateway.gatewaytoken.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.lesein.common.base.response.BaseResponse;
import com.lesein.common.base.util.JacksonUtil;
import com.lesein.common.base.util.OkHttpUtil;
import com.lesein.gateway.gatewaytoken.request.AnalysisRequest;
import com.lesein.gateway.gatewaytoken.response.AnalysisResponse;
import io.netty.buffer.ByteBufAllocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author WangJie
 * @date 2023/4/11
 */
@Component
public class GatewayTokenService implements GlobalFilter {
    private static final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

    /**
     * 排除不需要鉴权的url
     */
    private static final List<String> EXCLUDE_URLS = Lists.newArrayList("/authorization/auth/authorization");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        String currentUrl = exchange.getRequest().getURI().getPath();
        if(!EXCLUDE_URLS.contains(currentUrl)){
            if (token == null || "".equals(token)) {
                // 返回给前端登陆失效
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                BaseResponse baseResponse = new BaseResponse(false,HttpStatus.UNAUTHORIZED.value(),null, "登录失效");
                byte[] bytes = new byte[0];
                try {
                    bytes = new ObjectMapper().writeValueAsBytes(baseResponse);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                DataBuffer buffer = response.bufferFactory().wrap(bytes);
                return response.writeWith(Flux.just(buffer));
            }else {
                //解析token
                AnalysisRequest analysisRequest=new AnalysisRequest();
                analysisRequest.setToken(token);
                Map<String,String> header=new HashMap<>(2);
                header.put("token",token);
                BaseResponse<AnalysisResponse> baseResponse = OkHttpUtil.postByJson("http://localhost:8762/auth/analysis", analysisRequest, header, new TypeReference<BaseResponse<AnalysisResponse>>() {
                });
                AnalysisResponse data = baseResponse.getData();
                //获取requestBody，并增加从token中解析的数据
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders httpHeaders = request.getHeaders();
                if (httpHeaders.getContentLength() > 0) {
                    return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        String bodyString = new String(bytes, StandardCharsets.UTF_8);
                        JSONObject jsonObject =  JSON.parseObject(bodyString);
                        jsonObject.put("userId",data.getUserId());
                        String newBodyString=jsonObject.toString();

                        exchange.getAttributes().put("POST_BODY", newBodyString);
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    });
                }

            }
        }
        return chain.filter(exchange);
    }
}
