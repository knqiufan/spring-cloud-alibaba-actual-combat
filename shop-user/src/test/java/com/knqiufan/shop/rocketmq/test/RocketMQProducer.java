package com.knqiufan.shop.rocketmq.test;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 消息队列生产者
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/4/12 0:00
 */
public class RocketMQProducer {
    public static void main(String[] args) throws Exception {
        // 创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("knProducerGroup");
        // 设置NamesrvAddr的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 启动生产者
        producer.start();
        // 创建消息对象
        Message message = new Message("knTopic", "knTag", "你好，这是测试消息".getBytes());
        System.out.println("生产者发出的消息为：" + JSON.toJSONString(message));
        // 发送消息
        SendResult send = producer.send(message);
        System.out.println("生产者收到的发送结果信息为：" + JSON.toJSONString(send));
        // 关闭生产者
        producer.shutdown();
    }
}
