package com.baixing.hc.redpacket.controller;

import com.baixing.hc.redpacket.controller.vo.dto.RedPacketDetailDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketIdentityDTO;
import com.baixing.hc.redpacket.controller.vo.dto.RedPacketAmountDTO;
import com.baixing.hc.redpacket.controller.vo.req.CreateRedPacketReq;
import com.baixing.hc.redpacket.controller.vo.req.GrabRedPacketReq;
import com.baixing.hc.redpacket.exception.BusinessException;
import com.baixing.hc.redpacket.model.vo.RedPacketMeta;
import com.baixing.hc.redpacket.service.RedPacketService;
import com.baixing.ms.springtime.modules.api.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hucong
 * @date 2020/11/7 10:12 下午
 */
@RestController
@RequiredArgsConstructor
public class RedPacketController implements RedPacketApi {

    private final RedPacketService redPacketService;

    @Override
    public R<RedPacketIdentityDTO> createRedPacket(CreateRedPacketReq createRedPacketReq) {
        if (createRedPacketReq.getAmount() < createRedPacketReq.getTotal()) {
            throw new BusinessException("红包金额不能小于要发送的人数");
        }
        /**
         *  'redPacketId' => ''
         */
        return R.data(redPacketService.createRedPacket(createRedPacketReq));
    }

    @Override
    public R<RedPacketAmountDTO> grabRedPacket(GrabRedPacketReq grabRedPacketReq) {
        /**
         *  'amount' => ''
         */
        return R.data(redPacketService.grabRedPacket(grabRedPacketReq));
    }

    @Override
    public R<List<RedPacketMeta>> getActiveRedPacketList() {
        /**
         * [
         *   'user' => ''
         *   'redPacketId' => ''
         *   'createTime' => ''
         * ] ...
         */
        return R.data(redPacketService.getRedPacketActiveList());
    }

    @Override
    public R<List<RedPacketMeta>> getHistoryRedPacketList() {
        /**
         * [
         *   'user' => ''
         *   'redPacketId' => ''
         *   'createTime' => ''
         * ] ...
         */
        return R.data(redPacketService.getRedPacketHistoryList());
    }

    @Override
    public R<RedPacketDetailDTO> getRedpacketDetail(Long id) {
        /**
         *   'remainingNum' => ''
         *   'remainingAmount' => ''
         *   'redPacket' => {
         *     'id' => ''
         *     'user' => ''
         *     'createTime' => ''
         *     'amount' => ''
         *     'total' => ''
         *   }
         *   'records' => [
         *     {
         *       'user' => '',
         *       'amount' => '',
         *       'time' => ''
         *     }
         *   ]
         *
         */
        return R.data(redPacketService.getRedpacketDetail(id));
    }
}
