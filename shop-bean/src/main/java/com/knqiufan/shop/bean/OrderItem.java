package com.knqiufan.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.knqiufan.shop.utils.id.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 *
 * @author 黄语秋
 * @version 1.0.0
 * @date 2023/3/4 9:25
 */
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = -1329173923755780293L;
    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    @TableField("t_order_id")
    private Long orderId;

    /**
     * 商品id
     */
    @TableField("t_pro_id")
    private Long proId;

    /**
     * 商品名称
     */
    @TableField("t_pro_name")
    private String proName;

    /**
     * 商品价格（单价）
     */
    @TableField("t_pro_price")
    private BigDecimal proPrice;

    /**
     * 购买数量
     */
    @TableField("t_number")
    private Integer number;

    public OrderItem(){
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}
