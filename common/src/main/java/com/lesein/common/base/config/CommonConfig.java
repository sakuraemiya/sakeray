package com.lesein.common.base.config;

import com.lesein.common.base.aop.RestAspect;
import com.lesein.common.base.gateway.InitInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangJie
 * @date 2023/3/29
 */
@Configuration
public class CommonConfig {

    @Bean
    public RestAspect getRestAspect() {
        return new RestAspect();
    }

    @Bean
    public InitInterface getInitInterface(){
        return new InitInterface();
    }
}
