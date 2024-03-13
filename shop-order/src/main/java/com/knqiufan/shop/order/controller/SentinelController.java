package com.knqiufan.shop.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.knqiufan.shop.order.service.SentinelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Sentinel
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/23 2:05
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SentinelController {

    final SentinelService sentinelService;

    @GetMapping(value = "/request_sentinel1")
    public String requestSentinel1() {
        log.info("测试sentinel1");
        sentinelService.sendMsg();
        return "sentinel1";
    }

    @GetMapping(value = "/request_sentinel2")
    public String requestSentinel2() {
        log.info("测试sentinel2");
        sentinelService.sendMsg();
        return "sentinel2";
    }

    private int count = 0;

    @GetMapping(value = "/request_sentinel4")
    @SentinelResource("request_sentinel4")
    public String requestSentinel4() {
        log.info("request_sentinel4");
        count++;
        //模拟异常，比例为50%
        if (count % 2 == 0) {
            throw new RuntimeException("演示基于异常比例熔断");
        }
        return "request_sentinel4";
    }

    @GetMapping(value = "/request_sentinel3")
    @SentinelResource("request_sentinel3")
    public String requestSentinel3(String header, String body){
        log.info("测试Sentinel3");
        return "sentinel3";
    }

    @GetMapping(value = "/request_sentinel5")
    public String requestSentinel5(){
        log.info("测试request_sentinel5");
        return sentinelService.sendMsg2();
    }

    @GetMapping(value = "/request_sentinel6")
    public String requestSentinel6(){
        log.info("测试request_sentinel6");
        return sentinelService.sendMsg3();
    }
}
