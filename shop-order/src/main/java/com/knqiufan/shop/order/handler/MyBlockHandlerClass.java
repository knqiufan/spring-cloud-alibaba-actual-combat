package com.knqiufan.shop.order.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 类描述
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/23 23:38
 */
@Slf4j
public class MyBlockHandlerClass {

    public static String bkHandlerMethod(BlockException e) {
        log.error("block handler exception 限流了: {}", e.toString());
        return "限流嘛嘛嘛嘛";
    }
}
