package com.knqiufan.shop.gateway.filter;

import lombok.Data;

import java.io.Serializable;

/**
 * 接收配置参数配置类
 *
 * @author knqiufan
 * @version 1.0.0
 * @date 2023/4/2 17:40
 */
@Data
public class GrayscaleGatewayFilterConfig implements Serializable {

    private static final long serialVersionUID = 983019309000445082L;

    private boolean grayscale;

}
