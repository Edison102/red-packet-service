package com.baixing.hc.redpacket.controller.vo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hucong
 * @date 2020/11/7 10:33 下午
 */
@Data
public class CreateRedPacketReq {

    @NotBlank(message = "用户不能为空")
    private String user;

    @Min(1)
    private Integer total;

    @Min(1)
    private Integer amount;
}
