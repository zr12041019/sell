package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/3 16:50
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"订单完成"),
    CANCEL(2,"订单取消");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
