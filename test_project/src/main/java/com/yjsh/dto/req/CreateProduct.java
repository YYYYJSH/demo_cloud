package com.yjsh.dto.req;

import lombok.Data;

/**
 * @author 殷金帅
 * @date 2022-05-08 10:58
 **/
@Data
public class CreateProduct {

    /**
     * 商品id
     */
    private Integer skuId;

    /**
     * 商品数量
     */
    private Integer skuNum;

}
