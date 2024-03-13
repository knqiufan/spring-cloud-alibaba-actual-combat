package com.knqiufan.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.knqiufan.shop.bean.Order;
import com.knqiufan.shop.bean.OrderItem;
import com.knqiufan.shop.bean.Product;
import com.knqiufan.shop.bean.User;
import com.knqiufan.shop.order.mapper.OrderItemMapper;
import com.knqiufan.shop.order.mapper.OrderMapper;
import com.knqiufan.shop.order.service.OrderService;
import com.knqiufan.shop.param.OrderParams;
import com.knqiufan.shop.utils.constants.HttpCode;
import com.knqiufan.shop.utils.resp.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * 订单业务逻辑实现
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/3/4 9:12
 */
@Slf4j
@Service("orderServiceImpl")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final static String SERVER_USER_NAME = "server-user";

    final static String SERVER_PRODUCT_NAME = "server-product";

    final OrderMapper orderMapper;

    final OrderItemMapper orderItemMapper;

    final RestTemplate restTemplate;

    final DiscoveryClient discoveryClient;

    /**
     * 保存订单
     *
     * @param orderParams 订单参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()) {
            throw new RuntimeException("订单参数异常：" + JSONObject.toJSONString(orderParams));
        }
        User user = restTemplate.getForObject("http://" + SERVER_USER_NAME + "/user/get/" + orderParams.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("未获取到用户信息：" + JSONObject.toJSONString(orderParams));
        }
        Product product = restTemplate.getForObject("http://" + SERVER_PRODUCT_NAME + "/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null) {
            throw new RuntimeException("未获取到商品信息：" + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()) {
            throw new RuntimeException("商品库存不足：" + JSONObject.toJSONString(orderParams));
        }

        // 插入订单数据
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        // 更新商品库存
        Result<Integer> result = restTemplate.getForObject(
                "http://" + SERVER_PRODUCT_NAME + "/product/update_count/" + product.getId() + "/" + orderParams.getCount(),
                Result.class);
        if (HttpCode.SUCCESS != result.getCode()) {
            throw new RuntimeException("商品库存扣减失败！");
        }
        log.info("商品库存扣减成功。");
    }

    /**
     * 根据服务名获取服务地址
     *
     * @param serverName 服务名
     * @return 服务地址
     */
    private String getServiceUrl1(String serverName) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(serverName).get(0);
        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }

    /**
     * 自定义负载均衡
     *
     * @param serverName 服务名
     * @return 服务地址
     */
    private String getServiceUrlCustom(String serverName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
        int i = new Random().nextInt(instances.size() - 1);
        ServiceInstance serviceInstance = instances.get(i);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        log.info("自定义负载均衡后的服务地址：{}", url);
        return url;
    }
}
