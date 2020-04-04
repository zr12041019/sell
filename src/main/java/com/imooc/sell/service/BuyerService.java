package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;

/**
 * @Description: 买家
 * @author: zr
 * @date: 2020/4/5 0:05
 */
public interface BuyerService {

    /**
     *查询买家订单信息
     * @param openid openid
     * @param orderId orderId
     * @return OrderDto
     */
    OrderDto findOrderOne(String openid,String orderId);

    /**
     *取消买家订单
     * @param openid openid
     * @param orderId orderId
     * @return OrderDto
     */
    OrderDto cancelOrderOne(String openid,String orderId);

}
