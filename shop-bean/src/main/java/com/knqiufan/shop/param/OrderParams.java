package com.knqiufan.shop.param;

import lombok.Data;

/**
 * 订单参数
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/8 1:25
 */
@Data
public class OrderParams {

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 购买商品的数量
     */
    private Integer count;

    public boolean isEmpty() {
        return (productId == null || productId <= 0) ||
                (userId == null || userId <= 0) ||
                (count == null || count <= 0);
    }
}
