package com.knqiufan.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.knqiufan.shop.order.service.OrderService;
import com.knqiufan.shop.param.OrderParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/4 9:11
 */
@Slf4j
@RestController(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    // @Qualifier("orderServiceImpl2")
    final OrderService orderServiceImpl2;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams) {
        log.info("提交订单时传递的参数：{}", JSONObject.toJSONString(orderParams));
        orderServiceImpl2.saveOrder(orderParams);
        return "success";
    }
}
