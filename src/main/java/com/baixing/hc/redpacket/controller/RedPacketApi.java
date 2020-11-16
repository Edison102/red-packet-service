package com.baixing.hc.redpacket.controller;

import com.baixing.hc.redpacket.controller.vo.dto.RedPacketDetailDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketIdentityDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketAmountDTO;
import com.baixing.hc.redpacket.controller.vo.req.CreateRedPacketReq;
import com.baixing.hc.redpacket.controller.vo.req.GrabRedPacketReq;
import com.baixing.hc.redpacket.model.vo.RedPacketMeta;
import com.baixing.ms.springtime.modules.api.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hucong
 * @date 2020/11/7 10:11 下午
 */
@RequestMapping("/api/red-packet-service")
public interface RedPacketApi {

    /**
     * {
     *     'user' => ''
     *     'total' => ''
     *     'amount' => ''
     * }
     * @param createRedPacketReq
     * @return
     */
    @PostMapping(value = "/redpacket/create")
    R<RedPacketIdentityDTO> createRedPacket(@Validated @RequestBody CreateRedPacketReq createRedPacketReq);

    /**
     * {
     *     'user' => ''
     *     'redPacketId' => ''
     * }
     * @param grabRedPacketReq
     * @return
     */
    @PostMapping(value = "/redpacket/grab")
    R<RedPacketAmountDTO> grabRedPacket(@Validated @RequestBody GrabRedPacketReq grabRedPacketReq);

    @GetMapping(value = "/redpacket/active")
    R<List<RedPacketMeta>> getActiveRedPacketList();

    @GetMapping(value = "/redpacket/history")
    R<List<RedPacketMeta>> getHistoryRedPacketList();

    @GetMapping(value = "/redpacket/detail")
    R<RedPacketDetailDTO> getRedpacketDetail(@RequestParam("id") Long id);
}
