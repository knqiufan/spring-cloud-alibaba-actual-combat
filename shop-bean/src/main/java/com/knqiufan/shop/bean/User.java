package com.knqiufan.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.knqiufan.shop.utils.id.SnowFlakeFactory;
import com.knqiufan.shop.utils.password.PasswordUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户bean
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/4 0:02
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -7032479567987350240L;


    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String username;

    /**
     * 密码
     */
    @TableField("t_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;

    public User() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        //默认密码
        this.password = PasswordUtils.getPassword("123456");
    }
}
