package com.knqiufan.shop.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自定义过滤器模拟灰度发布
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/4/2 17:42
 */
@Component
public class GrayscaleGatewayFilterFactory extends AbstractGatewayFilterFactory<GrayscaleGatewayFilterConfig> {

    public GrayscaleGatewayFilterFactory() {
        super(GrayscaleGatewayFilterConfig.class);
    }

    @Override
    public GatewayFilter apply(GrayscaleGatewayFilterConfig config) {
        return (exchange, chain) -> {
            if(config.isGrayscale()) {
                System.out.println("开启了灰度发布");
            } else {
                System.out.println("关闭了灰度发布");
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("grayscale");
    }
}
