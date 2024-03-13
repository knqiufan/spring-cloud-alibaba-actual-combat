package com.knqiufan.shop.user.rocketmq;

import com.alibaba.fastjson.JSON;
import com.knqiufan.shop.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消费者监听
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/4/12 0:21
 */
@Component
@Slf4j
// 表示当前类是rocketmq消费者
@RocketMQMessageListener(consumerGroup = "user-group", topic = "order-topic")
public class RocketConsumerListener implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("用户微服务收到了订单消息：{}", JSON.toJSONString(order));
    }
}
