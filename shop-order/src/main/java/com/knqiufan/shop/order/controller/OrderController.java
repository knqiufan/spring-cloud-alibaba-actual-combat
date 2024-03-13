package com.knqiufan.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.knqiufan.shop.order.service.OrderService;
import com.knqiufan.shop.param.OrderParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/4 9:11
 */
@Slf4j
@RestController(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    // @Qualifier("orderServiceImpl2")
    final OrderService orderServiceImpl4;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams) {
        log.info("提交订单时传递的参数：{}", JSONObject.toJSONString(orderParams));
        orderServiceImpl4.saveOrder(orderParams);
        return "success";
    }

    @GetMapping(value = "/test_sentinel")
    public String test() {
        log.info("测试接口...");
        return "sentinel";
    }

    @GetMapping(value = "/test_sentinel2")
    public String test2() {
        log.info("测试接口2...");
        return "sentinel2";
    }
}
