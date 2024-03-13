package com.knqiufan.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.knqiufan.shop.bean.Order;
import com.knqiufan.shop.bean.OrderItem;
import com.knqiufan.shop.bean.Product;
import com.knqiufan.shop.bean.User;
import com.knqiufan.shop.order.feign.ProductService;
import com.knqiufan.shop.order.feign.UserService;
import com.knqiufan.shop.order.mapper.OrderItemMapper;
import com.knqiufan.shop.order.mapper.OrderMapper;
import com.knqiufan.shop.order.service.OrderService;
import com.knqiufan.shop.param.OrderParams;
import com.knqiufan.shop.utils.constants.HttpCode;
import com.knqiufan.shop.utils.resp.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 订单业务逻辑实现 -- Feign + Sentinel 容错
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/17 0:29
 */
@Slf4j
@Service("orderServiceImpl4")
@RequiredArgsConstructor
public class OrderServiceImpl4 implements OrderService {

    final OrderMapper orderMapper;

    final OrderItemMapper orderItemMapper;

    final UserService userService;

    final ProductService productService;

    final RocketMQTemplate rocketMQTemplate;

    /**
     * 保存订单
     *
     * @param orderParams 订单参数
     */
    @Override
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()) {
            throw new RuntimeException("订单参数异常：" + JSONObject.toJSONString(orderParams));
        }
        User user = userService.getUser(orderParams.getUserId());
        if (user == null) {
            throw new RuntimeException("未获取到用户信息：" + JSONObject.toJSONString(orderParams));
        }
        if (user.getId() == -1) {
            throw new RuntimeException("触发了用户微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        Product product = productService.get(orderParams.getProductId());
        if (product == null) {
            throw new RuntimeException("未获取到商品信息：" + JSONObject.toJSONString(orderParams));
        }
        if (product.getId() == -1) {
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()) {
            throw new RuntimeException("商品库存不足：" + JSONObject.toJSONString(orderParams));
        }

        // 插入订单数据
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        // 更新商品库存
        Result<Integer> result = productService.updateCount(orderParams.getProductId(), orderParams.getCount());
        if (result.getCode() == 1001) {
            throw new RuntimeException(result.getMsg() + ": " + JSONObject.toJSONString(orderParams));
        }
        if (HttpCode.SUCCESS != result.getCode()) {
            throw new RuntimeException("商品库存扣减失败！");
        }
        log.info("商品库存扣减成功。");

        // 发送库存消息
        rocketMQTemplate.convertAndSend("order-topic", order);
    }
}
