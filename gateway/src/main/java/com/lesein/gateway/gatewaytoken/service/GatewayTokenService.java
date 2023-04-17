package com.lesein.gateway.gatewaytoken.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.lesein.common.base.response.BaseResponse;
import com.lesein.common.base.util.OkHttpUtil;
import com.lesein.gateway.gatewaytoken.request.AnalysisRequest;
import com.lesein.gateway.gatewaytoken.response.AnalysisResponse;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageReader;
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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangJie
 * @date 2023/4/11
 */
@Component
public class GatewayTokenService implements GlobalFilter {
    private static final List<HttpMessageReader<?>> HTTP_MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();

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
            } else {
                //解析token
                AnalysisRequest analysisRequest=new AnalysisRequest();
                analysisRequest.setToken(token);
                Map<String,String> header=new HashMap<>(2);
                header.put("token",token);
                BaseResponse<AnalysisResponse> baseResponse = OkHttpUtil.postByJson("http://localhost:8762/auth/analysis", analysisRequest, header, new TypeReference<BaseResponse<AnalysisResponse>>() {
                });
                AnalysisResponse data = baseResponse.getData();
                //获取requestBody，并增加从token中解析的数据
                ServerRequest serverRequest = ServerRequest.create(exchange, HTTP_MESSAGE_READERS);
                Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                        .flatMap(body -> {
                            JSONObject jsonObject = JSONObject.parseObject(body);
                            jsonObject.put("userId",data.getUserId());
                            return Mono.just(jsonObject.toJSONString());
                        });
                BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(exchange.getRequest().getHeaders());

                headers.remove(HttpHeaders.CONTENT_LENGTH);

                CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
                return bodyInserter.insert(outputMessage, new BodyInserterContext())
                        .then(Mono.defer(() -> {
                            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
                                    exchange.getRequest()) {
                                @Override
                                public HttpHeaders getHeaders() {
                                    long contentLength = headers.getContentLength();
                                    HttpHeaders httpHeaders = new HttpHeaders();
                                    httpHeaders.putAll(super.getHeaders());
                                    if (contentLength > 0) {
                                        httpHeaders.setContentLength(contentLength);
                                    } else {
                                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                                    }
                                    return httpHeaders;
                                }
                                @Override
                                public Flux<DataBuffer> getBody() {
                                    return outputMessage.getBody();
                                }
                            };
                            return chain.filter(exchange.mutate().request(decorator).build());
                        }));

            }
        }
        return chain.filter(exchange);
    }
}
