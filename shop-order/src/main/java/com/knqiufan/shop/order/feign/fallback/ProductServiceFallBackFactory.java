package com.knqiufan.shop.order.feign.fallback;

import com.knqiufan.shop.bean.Product;
import com.knqiufan.shop.order.feign.ProductService;
import com.knqiufan.shop.utils.resp.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 商品微服务容错 Factory
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/17 0:41
 */
@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product get(Long pid) {
                Product product = new Product();
                product.setId(-1L);
                return product;
            }

            @Override
            public Result<Integer> updateCount(Long pid, Integer count) {
                Result<Integer> result = new Result<>();
                result.setCode(1001);
                result.setMsg("触发了容错逻辑");
                return result;
            }
        };
    }
}
