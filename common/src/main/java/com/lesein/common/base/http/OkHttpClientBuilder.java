package com.lesein.common.base.http;

import com.lesein.common.base.response.OkHttpClientResult;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author WangJie
 * @date 2023/6/30
 */
public class OkHttpClientBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpClientBuilder.class);

    /**
     * 同步阻塞等待执行结果的时间，单位毫秒
     */
    private static Integer DEFAULT_WAIT_TIMEOUT = 3000;

    /**
     * 服务启动时初始化OkHttpClient对象，确保客户端对象单例
     */
    private static OkHttpClient okHttpClient = OkHttpClientFactory.newInstance().build();

    private OkHttpClientBuilder() {}

    static {
        // 向JVM注册一个关闭钩子，当服务准备停止时，等待 OkHttpClient 中任务执行完毕再停止，防止线程池中正在执行的任务突然中断
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            final Logger logger = LoggerFactory.getLogger(OkHttpClient.class);
            int count = 100;
            Dispatcher dispatcher = okHttpClient.dispatcher();
            logger.info("ShutdownHook start：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
            while (dispatcher.queuedCallsCount() > 0 || dispatcher.runningCallsCount() > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error("ShutdownHook interrupted：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
                    break;
                }
                // 防止无限循环
                if (--count == 0) {
                    LOGGER.error("ShutdownHook timeout：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
                    break;
                }
            }
            logger.info("ShutdownHook end：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
        }));
    }

    /**
     * 同步执行请求，公共方法
     * @param request
     * @return
     */
    public static OkHttpClientResult syncRequest(Request request){
        try (Response response = okHttpClient.newCall(request).execute()){
            return buildResponseResult(request, response);
        } catch (Exception e) {
            LOGGER.error("request error，request:" +  request, e);
            return new OkHttpClientResult(false, 500, "request error");
        }
    }

    /**
     * 异步执行请求，同步阻塞编程等待返回结果
     * 此方式在多线程环境下请求处理依然能保持高性能，根据不同的场景显式对ConnectionPool进行调优处理
     * @param request
     * @return
     */
    public static OkHttpClientResult syncResponse(Request request){
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("request start，request:{}", request);
        }
        // 使用异步编程，在指定时间内阻塞获取 OKHttp 异步执行结果
        CompletableFuture<Response> completableFuture = new CompletableFuture<>();
        // 发起异步请求调用
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback(){

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                completableFuture.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                completableFuture.complete(response);
            }
        });
        // 这里的异步执行结果等待时间，取决于get同步获取时间的设定，而不是全局请求超时配置
        try (Response response = completableFuture.get(DEFAULT_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);) {
            return buildResponseResult(request, response);
        } catch (TimeoutException e){
            call.cancel();
            LOGGER.error("request timeout，request:" +  request, e);
            return new OkHttpClientResult(false, 500, "request timeout");
        } catch (Exception e){
            LOGGER.error("request error，request:" +  request, e);
            return new OkHttpClientResult(false, 500, "request error");
        }
    }


    /**
     * 封装返回值
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private static OkHttpClientResult buildResponseResult(Request request, Response response) throws IOException {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("request end，request:{}, response:{}", request, response);
        }
        ResponseBody responseBody = response.body();
        OkHttpClientResult result = new OkHttpClientResult();
        result.setSuccess(response.isSuccessful());
        result.setCode(response.code());
        result.setMessage(response.message());
        result.setHeaders(response.headers().toMultimap());
        if(Objects.nonNull(responseBody)){
            result.setBody(responseBody.bytes());
        }
        if(!result.isSuccess()){
            LOGGER.warn("request fail, request:{}, response:{}", request, response);
        }
        return result;
    }

}
