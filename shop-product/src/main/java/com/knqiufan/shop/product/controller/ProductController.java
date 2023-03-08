package com.knqiufan.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.knqiufan.shop.bean.Product;
import com.knqiufan.shop.product.service.ProductService;
import com.knqiufan.shop.utils.constants.HttpCode;
import com.knqiufan.shop.utils.resp.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Product接口
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:55
 */
@Slf4j
@RestController("/product")
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping("/get/{pid}")
    public Product get(@PathVariable("pid") Long pid) {
        Product product = productService.getProductById(pid);
        log.info("product get info: {}", JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping("/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid,
                                       @PathVariable("count") Integer count) {
        log.info("update product stock by pid: {}, buy count: {}", pid, count);
        int updateCount = productService.deductionProductStockById(pid, count);
        return new Result<>(HttpCode.SUCCESS, "success", updateCount);
    }
}
