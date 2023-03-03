package com.knqiufan.shop.product.service.impl;

import com.knqiufan.shop.bean.Product;
import com.knqiufan.shop.product.mapper.ProductMapper;
import com.knqiufan.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product接口实现
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:56
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductMapper productMapper;
    /**
     * 根据id获取商品实体
     *
     * @param pid 主键
     * @return 商品实体
     */
    @Override
    public Product getProductById(Long pid) {
        return productMapper.selectById(pid);
    }
    /**
     * 根据商品id扣减商品库存
     *
     * @param id    主键
     * @param count 扣减数量
     * @return 是否更新成功
     */
    @Override
    public int deductionProductStockById(Long id, Integer count) {
        return productMapper.deductionProductStockById(id, count);
    }
}
