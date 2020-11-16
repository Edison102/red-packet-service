package com.baixing.hc.redpacket.dao;

import com.baixing.hc.redpacket.model.domain.RedPacket;
import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hucong
 * @date 2020/11/14 10:50 下午
 */
@Component
@RequiredArgsConstructor
public class RedPacketDao {

    private final String REDPACKET_PREFIX = "redpacket:";
    private final RedisOperator<RedPacket> redisOperator;

    public void insert(RedPacket redPacket) {
        redisOperator.set(REDPACKET_PREFIX + redPacket.getId(), redPacket);
    }

    public RedPacket get(Long id) {
        if (id == null) {
            return null;
        }
        return redisOperator.get(REDPACKET_PREFIX + id);
    }
}
