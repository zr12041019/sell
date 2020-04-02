package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Description: 商品状态
 * @author: zr
 * @date: 2020/4/2 21:33
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"商品在架"),
    DOWN(1,"商品下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
