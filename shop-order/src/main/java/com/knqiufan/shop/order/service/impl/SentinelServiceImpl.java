package com.knqiufan.shop.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.knqiufan.shop.order.handler.MyBlockHandlerClass;
import com.knqiufan.shop.order.handler.MyFallbackClass;
import com.knqiufan.shop.order.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 链路流控模式测试
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/23 2:03
 */
@Slf4j
@Service
public class SentinelServiceImpl implements SentinelService {
    /**
     * 测试方法
     */
    @Override
    @SentinelResource("sendMsg")
    public void sendMsg() {
        System.out.println("测试sentinel的链路流控模式");
    }

    private int count = 0;

    /**
     * 测试方法
     */
    @Override
    @SentinelResource(
            value = "sendMsg2",
            blockHandler = "bkHandler",
            fallback = "fb"
    )
    public String sendMsg2() {
        count++;
        if (count % 4 == 0) {
            throw new RuntimeException("25%的异常率");
        }
        return "sendMsg2";
    }

    /**
     * 测试方法3
     */
    @Override
    @SentinelResource(
            value = "sendMsg3",
            blockHandlerClass = MyBlockHandlerClass.class,
            blockHandler = "bkHandlerMethod",
            fallbackClass = MyFallbackClass.class,
            fallback = "fbMethod"
    )
    public String sendMsg3() {
        count++;
        if (count % 4 == 0) {
            throw new RuntimeException("25%的异常率");
        }
        return "sendMsg3";
    }

    public String bkHandler(BlockException e) {
        log.error("限流了：{}", e.toString());
        return "限流了哇";
    }

    public String fb(Throwable e) {
        log.error("异常了：{}", e.toString());
        return "异常了哇";
    }
}
