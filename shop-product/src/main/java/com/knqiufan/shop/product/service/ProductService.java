package com.knqiufan.shop.product.service;

import com.knqiufan.shop.bean.Product;

/**
 * Product接口
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:55
 */
public interface ProductService {

    /**
     * 根据id获取商品实体
     *
     * @param pid 主键
     * @return 商品实体
     */
    Product getProductById(Long pid);

    /**
     * 根据商品id扣减商品库存
     *
     * @param id    主键
     * @param count 扣减数量
     * @return 是否更新成功
     */
    int deductionProductStockById(Long id, Integer count);
}
