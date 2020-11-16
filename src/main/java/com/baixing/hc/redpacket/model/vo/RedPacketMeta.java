package com.baixing.hc.redpacket.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hucong
 * @date 2020/11/14 10:06 下午
 */
@Data
public class RedPacketMeta {
    private String user;
    private Long redPacketId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
