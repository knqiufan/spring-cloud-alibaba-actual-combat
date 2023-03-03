package com.knqiufan.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knqiufan.shop.bean.Product;
import org.apache.ibatis.annotations.Param;

/**
 * ProductMapper
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:55
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 根据商品id扣减商品库存
     *
     * @param id    主键
     * @param count 扣减数量
     * @return 是否更新成功
     */
    int deductionProductStockById(@Param("id") Long id, @Param("count") Integer count);
}
