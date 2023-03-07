package com.knqiufan.shop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 订单服务启动类
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/4 9:06
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"com.knqiufan.shop.order.mapper"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
