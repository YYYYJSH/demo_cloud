package com.yjsh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* 测试订单商品表 Entity
*
* @author xx
* @date 2022-05-08 10:49:18
*/
@Data
@TableName("test_orders_product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestOrdersProductEntity  {

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 订单号
     */
    @TableField("orders_sn")
    private String ordersSn;

    /**
     * 商品id
     */
    @TableField("sku_id")
    private Integer skuId;

    /**
     * 商品数量
     */
    @TableField("sku_num")
    private Integer skuNum;

    /**
     * 商品状态：1-正常，2-售后
     */
    @TableField("sku_state")
    private Integer skuState;

    /**
     * 单件商品售价
     */
    @TableField("sku_price")
    private BigDecimal skuPrice;
    /**
     * 该商品总价格（单价 * 数量）
     */
    @TableField("total_price")
    private BigDecimal totalPrice;
    /**
     * 数据状态：0-无效，1-有效
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

}