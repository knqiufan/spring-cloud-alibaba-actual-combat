package com.knqiufan.shop.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/4/2 18:04
 */
@Slf4j
@Component
public class CustomGlobalGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 记录开始时间
     */
    private final static String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                log.info("访问接口主机：{}", exchange.getRequest().getURI().getHost());
                log.info("访问接口端口：{}", exchange.getRequest().getURI().getPort());
                log.info("访问接口URL：{}", exchange.getRequest().getURI().getPath());
                log.info("访问接口参数：{}", exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口时长：{}ms", System.currentTimeMillis() - beginVisitTime);
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
