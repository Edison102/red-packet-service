package com.baixing.hc.redpacket.dao;

import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author hucong
 * @date 2020/11/14 10:19 下午
 */
@Component
@RequiredArgsConstructor
public class RedPacketActiveDao {

    private final String ACTIVE_KET = "redpacket:active:";
    private final RedisOperator<Long> redisOperator;

    public void insert(Long redPacketId) {
        redisOperator.sadd(ACTIVE_KET, redPacketId);
    }

    public Boolean isMember(Long redPacketId) {
        return redisOperator.sismember(ACTIVE_KET, redPacketId);
    }

    public Boolean delete(Long redPacketId) {
        return redisOperator.sremove(ACTIVE_KET, redPacketId) > 0;
    }

    public Set<Long> getAll() {
        return redisOperator.sget(ACTIVE_KET);
    }
}
