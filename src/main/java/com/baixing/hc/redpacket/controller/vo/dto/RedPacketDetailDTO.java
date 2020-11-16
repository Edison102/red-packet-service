package com.baixing.hc.redpacket.controller.vo.dto;

import com.baixing.hc.redpacket.model.domain.RedPacket;
import com.baixing.hc.redpacket.model.domain.RedPacketRecord;
import lombok.Data;

import java.util.List;

/**
 * @author hucong
 * @date 2020/11/14 10:00 下午
 */
@Data
public class RedPacketDetailDTO {
    private Integer remainingNum;
    private Integer remainingAmount;
    private RedPacket redPacket;
    private List<RedPacketRecord> records;
}
