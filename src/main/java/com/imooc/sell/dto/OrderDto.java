package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/3 18:15
 */

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderDto {


    /**
     * 订单主键
     */
    private String orderId;

    /**
     *买家姓名
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信id
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 默认0为新下订单
     */
    private Integer orderStatus;

    /**
     * 支付状态 默认0未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;


    /**
     * 订单详情表
     */
    private List<OrderDetail> orderDetailList;
}
