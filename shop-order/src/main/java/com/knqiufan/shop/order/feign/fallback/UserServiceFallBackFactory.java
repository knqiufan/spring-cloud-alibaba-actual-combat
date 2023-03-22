package com.knqiufan.shop.order.feign.fallback;

import com.knqiufan.shop.bean.User;
import com.knqiufan.shop.order.feign.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户微服务容错 Factory
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/17 0:39
 */
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User getUser(long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}
