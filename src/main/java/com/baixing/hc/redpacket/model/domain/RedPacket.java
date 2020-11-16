package com.baixing.hc.redpacket.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hucong
 * @date 2020/11/8 2:18 下午
 */
@Data
public class RedPacket {
    private Long id;
    private String user;
    private Integer amount;  //金额
    private Integer total;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
