package com.baixing.hc.redpacket.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hucong
 * @date 2020/11/8 6:45 下午
 */
@Service
@RequiredArgsConstructor
public class RedisOperatorImpl<T> implements RedisOperator<T> {

    @Autowired
    @Qualifier("redisJsonTemplate")
    private final RedisTemplate redisTemplate;

    @Override
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, T value, long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    @Override
    public T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public Long sadd(String key, T value) {
        return redisTemplate.<String, T>opsForSet().add("set:" + key, value);
    }

    @Override
    public Long sremove(String key, T value) {
        return redisTemplate.<String, T>opsForSet().remove("set:" + key, value);
    }

    @Override
    public Boolean sismember(String key, T value) {
        return redisTemplate.<String, T>opsForSet().isMember("set:" + key, value);
    }

    @Override
    public Set<T> sget(String key) {
        return redisTemplate.<T>opsForSet().members("set:" + key);
    }

    @Override
    public void rpush(String key, T value) {
        redisTemplate.opsForList().rightPush("list:" + key, value);
    }

    @Override
    public T lpop(String key) {
        return (T) redisTemplate.opsForList().leftPop("list:" + key);
    }

    @Override
    public List<T> lrangeAll(String key) {
        return redisTemplate.<T>opsForList().range("list:" + key, 0, -1);
    }
}
