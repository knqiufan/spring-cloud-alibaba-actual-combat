package com.knqiufan.shop.utils.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回的结果数据
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/4 1:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1497405107265595284L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;
}
