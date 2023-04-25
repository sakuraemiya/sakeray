package com.lesein.common.base.gateway;

import com.google.common.collect.Lists;
import com.lesein.common.base.exception.BusinessValidateException;
import com.lesein.common.base.gateway.entity.GatewayUrl;
import com.lesein.common.base.util.OkHttpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author WangJie
 * @date 2023/3/31
 */
public class InitInterface implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String id;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args){
        if(!Objects.equals(id,"gateway")){
            List<GatewayUrl> registerList = new ArrayList<>();
            Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);
            for (Object bean : beans.values()) {
                GatewayUrl gatewayUrl = new GatewayUrl();
                gatewayUrl.setUniqueId(id);
                Class<?> aClass = bean.getClass();
                RequestMapping requestMapping = AnnotationUtils.findAnnotation(aClass, RequestMapping.class);
                if (Objects.nonNull(requestMapping)) {
                    String url = requestMapping.value()[0];
                    if(StringUtils.isBlank(url)){
                        throw new BusinessValidateException("项目启动失败，RequestMapping注解value值为空，类名为："+bean.getClass().getName());
                    }
                    if (url.startsWith("/")) {
                        String regex = "^/.+$";
                        if(!url.matches(regex)){
                            throw new BusinessValidateException("项目启动失败，RequestMapping注解value值不合法，以/开头后面必须包含字符，类名为："+bean.getClass().getName());
                        }
                        gatewayUrl.setPath(url + "/**");
                    } else {
                        gatewayUrl.setPath("/" + url + "/**");
                    }
                }else {
                    throw new BusinessValidateException("项目启动失败，RequestMapping注解缺失，类名为："+bean.getClass().getName());
                }
                registerList.add(gatewayUrl);
            }
            if(CollectionUtils.isNotEmpty(registerList)){
                OkHttpUtil.postByJson("http://localhost:8763/gatewayUrlApi/addUrl", Lists.newArrayList(registerList), null);
            }
        }
    }
}
