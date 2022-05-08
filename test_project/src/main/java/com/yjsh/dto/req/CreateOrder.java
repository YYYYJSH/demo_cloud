package com.yjsh.dto.req;

import lombok.Data;

import java.util.List;

/**
 * @author 殷金帅
 * @date 2022-05-08 10:56
 **/
@Data
public class CreateOrder {

    private String userId;

    private List<CreateProduct> products;

}
