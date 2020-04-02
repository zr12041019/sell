package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Description: 返回类型枚举类
 * @author: zr
 * @date: 2020/4/3 20:01
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1001,"商品不存在"),
    PRODUCT_STOCK_ERROR(1002,"商品库存不足"),
    ORDER_NOT_EXIST(1003,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(1004,"订单详情不存在"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
