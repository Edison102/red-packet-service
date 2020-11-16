package com.baixing.hc.redpacket.service;

import com.baixing.hc.redpacket.controller.vo.dto.RedPacketDetailDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketIdentityDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketAmountDTO;
import com.baixing.hc.redpacket.controller.vo.req.CreateRedPacketReq;
import com.baixing.hc.redpacket.controller.vo.req.GrabRedPacketReq;
import com.baixing.hc.redpacket.model.vo.RedPacketMeta;

import java.util.List;

/**
 * @author hucong
 * @date 2020/11/8 2:19 下午
 */
public interface RedPacketService {

    RedPacketIdentityDTO createRedPacket(CreateRedPacketReq createRedPacketReq);

    RedPacketAmountDTO grabRedPacket(GrabRedPacketReq grabRedPacketReq);

    List<RedPacketMeta> getRedPacketActiveList();

    List<RedPacketMeta> getRedPacketHistoryList();

    RedPacketDetailDTO getRedpacketDetail(Long id);
}
