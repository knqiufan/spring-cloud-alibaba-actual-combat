package com.knqiufan.shop.order.feign.fallback;

import com.knqiufan.shop.bean.User;
import com.knqiufan.shop.order.feign.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * UserService 接口容错类
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/17 0:20
 */
@Component
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
