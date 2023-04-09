package com.knqiufan.shop.user.service;

import com.knqiufan.shop.bean.User;

/**
 * User接口
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:54
 */
public interface UserService {
    /**
     * 根据id获取用户实体
     *
     * @param id 主键
     * @return 用户实体
     */
    User getUserById(long id);

    /**
     * 异步测试方法
     */
    void asyncMethod();
}
