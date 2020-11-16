package com.baixing.hc.redpacket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.template.Template;

/**
 * @author hucong
 * @date 2020/11/15 2:38 下午
 */
@RestController
@RequiredArgsConstructor
public class ViewController {

    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/api/red-packet-service/index")
    public String index() throws Exception {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("index.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, null);
        return html;
    }

}
