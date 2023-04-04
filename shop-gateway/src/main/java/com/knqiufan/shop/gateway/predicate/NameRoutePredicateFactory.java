package com.knqiufan.shop.gateway.predicate;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言功能
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/4/2 16:02
 */
@Component
public class NameRoutePredicateFactory extends AbstractRoutePredicateFactory<NameRoutePredicateConfig> {

    public NameRoutePredicateFactory() {
        super(NameRoutePredicateConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(NameRoutePredicateConfig config) {
        return serverWebExchange -> {
            String name = serverWebExchange.getRequest().getQueryParams().getFirst("name");
            if (StringUtils.isEmpty(name)) {
                name = "";
            }
            return name.equals(config.getName());
        };
    }

    /**
     * 返回实体类中定义的属性，只有在这里定义接收了，在apply方法中才能接收到赋值的属性参数
     * 如果自定义的实体中有多个属性需要判断，shortcutFieldOrder()方法中的顺序要跟配置文件中的参数顺序一致
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("name");
    }
}
