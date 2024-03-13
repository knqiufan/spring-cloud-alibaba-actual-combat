package com.knqiufan.shop.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 网关全局过滤器
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/4/2 17:48
 */
@Configuration
@Slf4j
public class GatewayFilterConfig {

    @Bean
    @Order(1)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            log.info("执行前置过滤器");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("执行后置过滤器");
            }));
        };
    }
}
