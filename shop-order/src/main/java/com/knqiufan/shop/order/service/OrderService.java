package com.knqiufan.shop.order.service;

import com.knqiufan.shop.param.OrderParams;

/**
 * 订单业务逻辑接口
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/4 9:12
 */
public interface OrderService {

    /**
     * 保存订单
     *
     * @param orderParams 订单参数
     */
    void saveOrder(OrderParams orderParams);
}
