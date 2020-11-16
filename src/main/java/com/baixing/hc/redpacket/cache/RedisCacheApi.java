package com.baixing.hc.redpacket.cache;

import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisCacheApi<T> {

    private final RedisOperator<T> redisOperator;

    /**
     * 默认缓存时间为 == 1天
     */
    private Integer ttl = 24 * 60 * 60;

    public void setCache(String key, T value, long ttl) {
        redisOperator.set(key, value, ttl);
    }

    public void setCache(String key, T value) {
        redisOperator.set(key, value, ttl);
    }

    public T getCache(String key) {
        return redisOperator.get(key);
    }

}
