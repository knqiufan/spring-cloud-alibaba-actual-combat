package com.knqiufan.shop.order.feign;

import com.knqiufan.shop.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 类描述
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/14 0:33
 */
@FeignClient("server-user")
public interface UserService {

    @GetMapping("/user/get/{uid}")
    User getUser(@PathVariable("uid") long uid);
}
