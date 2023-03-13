package com.knqiufan.shop.order.feign;

import com.knqiufan.shop.bean.Product;
import com.knqiufan.shop.utils.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 类描述
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/14 0:36
 */
@FeignClient("server-product")
public interface ProductService {

    @GetMapping("/product/get/{pid}")
    Product get(@PathVariable("pid") Long pid);

    @GetMapping("/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid,
                                @PathVariable("count") Integer count);
}
