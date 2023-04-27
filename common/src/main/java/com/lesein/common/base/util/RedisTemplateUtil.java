package com.lesein.common.base.util;

import org.slf4j.Logger;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import redis.clients.jedis.Jedis;

/**
 * @author WangJie
 * @date 2023/4/27
 */
public class RedisTemplateUtil {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedisTemplateUtil.class);

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisTemplateUtil(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    private Jedis getJedis() {
        return (Jedis) redisConnectionFactory.getConnection().getNativeConnection();
    }

    /**
     * 保存
     *
     * @param key
     * @param dataString
     * @param seconds
     * @return
     */
    public Boolean saveString(String key, String dataString, int seconds) {
        try (Jedis jedis = getJedis()) {
            String set = jedis.set(key, dataString);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
            return "OK".equals(set);
        } catch (Exception ex) {
            log.error("serialize error.[key:" + key + "]", ex);
        }
        return false;
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.get(key);
        }
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        try (Jedis jedis = getJedis()) {
            return jedis.exists(key);
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, int expire) {
        try (Jedis jedis = getJedis()) {
            Long result = jedis.expire(key, expire);
            return result != null && result == 1;
        }
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public Boolean del(String key) {
        try (Jedis jedis = getJedis()) {
            Long result = jedis.del(key);
            return result != null && result == 1;
        } catch (Exception ex) {
            log.error("serialize error.[key:" + key + "]", ex);
            return false;
        }
    }


}
