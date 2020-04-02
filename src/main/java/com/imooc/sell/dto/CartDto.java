package com.imooc.sell.dto;

import lombok.Data;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/3 20:56
 */
@Data
public class CartDto {

    /**
     *商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDto() {
    }

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
