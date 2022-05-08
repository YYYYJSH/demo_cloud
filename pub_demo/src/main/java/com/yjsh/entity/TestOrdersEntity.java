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
* 测试订单表 Entity
*
* @author xx
* @date 2022-05-08 10:47:31
*/
@Data
@TableName("test_orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestOrdersEntity  {

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 订单金额
     */
    @TableField("order_money")
    private BigDecimal orderMoney;
    /**
     * 退款金额
     */
    @TableField("back_money")
    private BigDecimal backMoney;
    /**
     * 订单状态：1-待付款，2-待发货，3-待收货，4-已签收，5-已完成，6-已取消
     */
    @TableField("order_state")
    private Integer orderState;

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