package com.baixing.hc.redpacket.dao;

import com.baixing.hc.redpacket.model.domain.RedPacketRecord;
import com.baixing.hc.redpacket.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hucong
 * @date 2020/11/15 5:21 下午
 */
@Component
@RequiredArgsConstructor
public class RepealUserDao {

    private final String REPEAL_USER_PREFIX = "redpacket:repealuser:";
    private final RedisOperator<String> redisOperator;

    public Boolean insert(Long redPacketId, String user) {
        return redisOperator.sadd(REPEAL_USER_PREFIX + redPacketId, user) > 0;
    }

    public void delete(Long redPacketId, String user) {
        redisOperator.sremove(REPEAL_USER_PREFIX + redPacketId, user);
    }
}
