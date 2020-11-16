package com.baixing.hc.redpacket.controller.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hucong
 * @date 2020/11/7 10:36 下午
 */
@Data
public class GrabRedPacketReq {
    @NotBlank(message = "抢红包的用户不能为空")
    private String user;

    @NotNull
    private Long redPacketId;
}
