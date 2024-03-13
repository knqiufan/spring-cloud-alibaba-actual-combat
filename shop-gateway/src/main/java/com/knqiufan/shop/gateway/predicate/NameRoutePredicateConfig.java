package com.knqiufan.shop.gateway.predicate;

import lombok.Data;

import java.io.Serializable;

/**
 * 接受配置文件参数
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/4/2 15:58
 */
@Data
public class NameRoutePredicateConfig implements Serializable {

    private static final long serialVersionUID = -3289515863427972825L;

    private String name;
}
