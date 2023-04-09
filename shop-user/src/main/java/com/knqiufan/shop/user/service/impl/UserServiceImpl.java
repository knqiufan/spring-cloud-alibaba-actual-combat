package com.knqiufan.shop.user.service.impl;

import com.knqiufan.shop.bean.User;
import com.knqiufan.shop.user.mapper.UserMapper;
import com.knqiufan.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * User接口实现
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:54
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;

    /**
     * 根据id获取用户实体
     *
     * @param id 主键
     * @return 用户实体
     */
    @Override
    public User getUserById(long id) {
        return userMapper.selectById(id);
    }

    /**
     * 异步测试方法
     */
    @Async
    @Override
    public void asyncMethod() {
        log.info("执行了异步方法...");
    }
}
