package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @Description: 返回类型枚举类
 * @author: zr
 * @date: 2020/4/3 20:01
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(1001,"商品不存在"),

    PRODUCT_STOCK_ERROR(1002,"商品库存不足"),

    ORDER_NOT_EXIST(1003,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(1004,"订单详情不存在"),

    ORDER_STATUS_ERROR(1005,"订单状态不正确"),

    ORDER_UPDATE_FAIL(1006,"订单更新失败"),

    ORDER_DETAIL_EMPTY(1007,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(1008,"订单支付状态不正确"),

    CART_EMPTY(1009,"购物车为空"),

    ORDER_OWNER_ERROR(1010,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(1011,"微信公众账号异常"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(1012,"微信支付金额不一致"),

    ORDER_CANCEL_SUCCESSS(1013,"订单取消成功"),

    ORDER_FINISH_SUCCESSS(1013,"订单完结成功"),

    PRODUCT_STATUS_ERROR(1014,"商品状态不正确");
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
