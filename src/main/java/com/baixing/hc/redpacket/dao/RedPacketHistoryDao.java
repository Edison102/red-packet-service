package com.baixing.hc.redpacket.dao;

import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hucong
 * @date 2020/11/15 8:45 下午
 */
@Component
@RequiredArgsConstructor
public class RedPacketHistoryDao {

    private final String HISTORY_KET = "redpacket:history:";
    private final RedisOperator<Long> redisOperator;

    public void insert(Long redPacketId) {
        redisOperator.rpush(HISTORY_KET, redPacketId);
    }

    public List<Long> getAll() {
        return redisOperator.lrangeAll(HISTORY_KET);
    }
}
