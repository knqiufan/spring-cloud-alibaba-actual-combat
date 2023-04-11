package com.knqiufan.shop.rocketmq.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 消费者
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/4/12 0:10
 */
public class RocketMQConsumer {

    public static void main(String[] args) throws Exception {
        // 创建消息消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("knConsumerGroup");
        // 设置NamesrvAddr地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        // 订阅主题
        consumer.subscribe("knTopic", "*");
        // 设置消息监听，当收到消息时，会回调消息监听
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            System.out.println("消费者收到的消息为：" + list);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 启动消费者
        consumer.start();
        System.out.println("消费者启动");
    }
}
