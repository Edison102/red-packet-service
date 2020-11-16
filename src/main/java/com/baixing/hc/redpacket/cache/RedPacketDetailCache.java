package com.baixing.hc.redpacket.cache;

import com.baixing.hc.redpacket.controller.vo.dto.RedPacketDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hucong
 * @date 2020/11/15 12:47 下午
 */
@Component
@RequiredArgsConstructor
public class RedPacketDetailCache {

    private final String DETAIL_PREFIX = "redpacket:detail:";
    private final RedisCacheApi<RedPacketDetailDTO> redisCacheApi;

    public void set(Long redPacketId, RedPacketDetailDTO redPacketDetailDTO) {
        redisCacheApi.setCache(DETAIL_PREFIX + redPacketId, redPacketDetailDTO);
    }

    public RedPacketDetailDTO get(Long redPacketId) {
        return redisCacheApi.getCache(DETAIL_PREFIX + redPacketId);
    }
}
