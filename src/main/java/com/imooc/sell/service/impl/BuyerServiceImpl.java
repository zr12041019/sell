package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/5 0:10
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    /**
     * 查询买家订单信息
     *
     * @param openid  openid
     * @param orderId orderId
     * @return OrderDto
     */
    @Override
    public OrderDto findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid,orderId);
    }

    /**
     * 取消买家订单
     *
     * @param openid  openid
     * @param orderId orderId
     * @return OrderDto
     */
    @Override
    public OrderDto cancelOrderOne(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if(null == orderDto){
            log.error("【取消订单】 查不到该订单, orderId = {}" ,orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId){
        OrderDto orderDto = orderService.findOne(orderId);

        if(orderDto == null){
            return null;
        }

        if(! orderDto.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】 订单的openid不一致, openid = {} , orderId = {} ",openid,orderId );
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
