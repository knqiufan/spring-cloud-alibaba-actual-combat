package com.knqiufan.shop.utils.password;

import com.knqiufan.shop.utils.md5.MD5Hash;

/**
 * 密码生成工具
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/4 0:15
 */
public class PasswordUtils {

    /**
     * 生成默认md5密码串
     *
     * @param password 密码
     * @return 默认md5密码串
     */
    public static String getPassword(String password) {
        if (password == null || password.trim().isEmpty()) return password;
        for (int i = 0; i < 5; i++) {
            password = MD5Hash.md5Java(password);
        }
        return password;
    }
}
