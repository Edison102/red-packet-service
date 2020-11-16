package com.baixing.hc.redpacket.dao;

import com.baixing.hc.redpacket.model.domain.RedPacketRecord;
import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hucong
 * @date 2020/11/14 11:36 下午
 */
@Component
@RequiredArgsConstructor
public class RedPacketRecordDao {

    private final String REDPACKET_RECORD_PREFIX = "redpacket:record:";
    private final RedisOperator<RedPacketRecord> redisOperator;

    public void insert(Long redPacketId, RedPacketRecord redPacketRecord) {
        redisOperator.rpush(REDPACKET_RECORD_PREFIX + redPacketId, redPacketRecord);
    }

    public List<RedPacketRecord> getAll(Long redPacketId) {
        List<RedPacketRecord> redPacketRecordList = redisOperator.lrangeAll(REDPACKET_RECORD_PREFIX + redPacketId);
        return redPacketRecordList != null ? redPacketRecordList : new ArrayList<>();
    }
}
