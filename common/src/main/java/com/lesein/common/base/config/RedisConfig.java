package com.lesein.common.base.config;

import com.lesein.common.base.util.RedisTemplateUtil;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author WangJie
 * @date 2023/4/26
 */
@Configuration
public class RedisConfig {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisTemplateUtil redisTemplateUtils(RedisConnectionFactory redisConnectionFactory) {
        log.info("初始化RedisTemplateUtils: {}", redisConnectionFactory.getClass().getName());
        return new RedisTemplateUtil(redisConnectionFactory);
    }

}
