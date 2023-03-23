package com.knqiufan.shop.order.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/23 23:39
 */
@Slf4j
public class MyFallbackClass {

    public static String fbMethod(Throwable e) {
        log.error("fallback class 异常了：{}" ,e.toString());
        return "异常了得得得得";
    }
}
