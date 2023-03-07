package com.knqiufan.shop.param;

import lombok.Data;

/**
 * 订单参数
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/8 1:25
 */
@Data
public class OrderParams {

    private Long productId;

    private Long userId;

    private Integer count;

    public boolean isEmpty() {
        return (productId == null || productId <= 0) ||
                (userId == null || userId <= 0) ||
                (count == null || count <= 0);
    }
}
