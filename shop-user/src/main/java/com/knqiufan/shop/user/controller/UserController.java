package com.knqiufan.shop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.knqiufan.shop.bean.User;
import com.knqiufan.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User接口类
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/2/24 0:54
 */
@Slf4j
@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/get/{uid}")
    public User getUser(@PathVariable("uid") long uid) {
        User user = userService.getUserById(uid);
        log.info("get user info: {}", JSONObject.toJSONString(user));
        return user;
    }

    @GetMapping("/api/apiFilter")
    public String apiFilter(HttpServletRequest req, HttpServletResponse resp) {
        log.info("访问了apiFilter接口");
        String ip = req.getHeader("IP");
        String name = req.getParameter("name");
        log.info("ip:{}, name:{}", ip, name);
        return "apiFilter";
    }
}
