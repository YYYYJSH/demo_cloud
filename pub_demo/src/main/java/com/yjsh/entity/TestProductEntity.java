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
* 测试商品表 Entity
*
* @author xx
* @date 2022-05-08 10:49:43
*/
@Data
@TableName("test_product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestProductEntity   {

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名字
     */
    @TableField("sku_name")
    private String skuName;

    /**
     * 商品价格
     */
    @TableField("sku_price")
    private BigDecimal skuPrice;
    /**
     * 商品库存
     */
    @TableField("sku_inventory")
    private Integer skuInventory;

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