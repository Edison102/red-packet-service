package com.baixing.hc.redpacket.dao;

import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hucong
 * @date 2020/11/15 1:18 上午
 */
@Component
@RequiredArgsConstructor
public class RedPacketAmountDao {

    private final String GRAB_PREFIX = "redpacket:grab:";
    private final RedisOperator<Integer> redisOperator;

    public void insert(Long redPacketId, Integer amount) {
        redisOperator.rpush(GRAB_PREFIX + redPacketId, amount);
    }

    public Integer grab(Long redPacketId) {
        return redisOperator.lpop(GRAB_PREFIX + redPacketId);
    }
}
