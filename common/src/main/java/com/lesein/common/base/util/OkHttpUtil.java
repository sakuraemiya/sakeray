package com.lesein.common.base.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lesein.common.base.config.HttpConfig;
import com.lesein.common.base.exception.BusinessValidateException;
import com.lesein.common.base.response.OkHttpClientResult;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

/**
 * @author WangJie
 * @date 2023/4/11
 */
public class OkHttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpUtil.class);

    private static final OkHttpClient client = HttpConfig.newInstance().build();

    /**
     * get请求
     * @param url
     * @param headers
     * @return
     */
    public static String get(String url, Map<String, String> headers){
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .get()
                .build();
        OkHttpClientResult result = syncCommonRequest(request);
        return buildResponse(result);
    }

    /**
     * get请求（支持范型对象返回参数）
     * @param url
     * @param headers
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T get(String url, Map<String, String> headers, TypeReference<T> responseType){
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .get()
                .build();
        OkHttpClientResult result = syncCommonRequest(request);
        return buildResponse(result, responseType);
    }

    /**
     * post表单请求
     * @param url
     * @param paramMap
     * @param headers
     * @return
     */
    public static String postByForm(String url, Map<String, String> paramMap, Map<String, String> headers){
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildFormBody(paramMap))
                .build();
        OkHttpClientResult result = syncCommonRequest(request);
        return buildResponse(result);
    }

    /**
     * post表单请求（支持范型对象返回参数）
     * @param url
     * @param paramMap
     * @param headers
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T postByForm(String url, Map<String, String> paramMap, Map<String, String> headers, TypeReference<T> responseType){
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildFormBody(paramMap))
                .build();
        OkHttpClientResult result = syncCommonRequest(request);
        return buildResponse(result, responseType);
    }


    /**
     * post + json请求
     * @param url
     * @param value
     * @param headers
     * @return
     */
    public static String postByJson(String url, Object value, Map<String, String> headers){
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildJsonBody(value))
                .build();
        OkHttpClientResult result = syncCommonRequest(request);
        return buildResponse(result);
    }


    /**
     * post + json请求（支持范型对象返回参数）
     * @param url
     * @param value
     * @param headers
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T postByJson(String url, Object value, Map<String, String> headers, TypeReference<T> responseType){
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildJsonBody(value))
                .build();
        OkHttpClientResult result = syncCommonRequest(request);
        return buildResponse(result, responseType);
    }


    /**
     * 包装请求头部
     * @param headers
     * @return
     */
    private static Headers buildHeaders(Map<String, String> headers){
        Headers.Builder headerBuilder = new Headers.Builder();
        if(MapUtils.isNotEmpty(headers)){
            headers.forEach(headerBuilder::add);
        }
        return headerBuilder.build();
    }


    /**
     * 包装请求表单
     * @param paramMap
     * @return
     */
    private static FormBody buildFormBody(Map<String, String> paramMap){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if(MapUtils.isNotEmpty(paramMap)){
            paramMap.forEach(formBodyBuilder::add);
        }
        return formBodyBuilder.build();
    }

    /**
     * 包装请求json数据
     * @param request
     * @return
     */
    private static RequestBody buildJsonBody(Object request){
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(contentType, JacksonUtil.toJson(request));
        return requestBody;
    }

    /**
     * 包装返回结果，针对字符串
     * @param result
     * @return
     */
    private static String buildResponse(OkHttpClientResult result){
        if(!result.isSuccess() && StringUtils.isNotBlank(result.getMessage())){
            throw new BusinessValidateException(result.getMessage());
        }
        return byteToString(result.getBody());
    }

    /**
     * 包装返回结果，针对返回范型对象
     * @param result
     * @return
     */
    private static <T> T buildResponse(OkHttpClientResult result, TypeReference<T> responseType){
        if(!result.isSuccess() && StringUtils.isNotBlank(result.getMessage())){
            throw new BusinessValidateException(result.getMessage());
        }
        return JacksonUtil.byteToObj(result.getBody(), responseType);
    }

    /**
     * 获取内容
     * @param bytes
     * @return
     */
    private static String byteToString(byte[] bytes){
        if(Objects.nonNull(bytes)){
            return new String(bytes, Charset.forName("utf-8"));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 同步请求，公共方法
     * @param request
     * @return
     */
    private static OkHttpClientResult syncCommonRequest(Request request){
        try (Response response = client.newCall(request).execute()){
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
                LOGGER.warn("请求异常，request:{}，httpResponse:{}， responseBody:{}", new Object[]{request, response, byteToString(result.getBody())});
            }
            return result;
        } catch (Exception e) {
            return new OkHttpClientResult(false, 500, "invoke upstream timeout");
        }
    }
}
