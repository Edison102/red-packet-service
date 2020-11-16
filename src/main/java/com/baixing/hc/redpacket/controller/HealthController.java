package com.baixing.hc.redpacket.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hucong
 * @date 2020/11/14 11:21 下午
 */
@RestController
public class HealthController {

    @GetMapping(value = "/api/red-packet-service/health")
    public Object health() {
        return Health.up()
                .withDetail("message", "I'm still alive")
                .build();
    }
}
