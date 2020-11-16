package com.baixing.hc.redpacket.redis;

import java.util.List;
import java.util.Set;

/**
 * @author hucong
 * @date 2020/11/8 6:44 下午
 */
public interface RedisOperator<T> {
    void set(String key, T value);

    void set(String key, T value, long ttl);

    T get(String key);

    Long sadd(String key, T value);

    Long sremove(String key, T value);

    Boolean sismember(String key, T value);

    Set<T> sget(String key);

    void rpush(String key, T value);

    T lpop(String key);

    List<T> lrangeAll(String key);
}
