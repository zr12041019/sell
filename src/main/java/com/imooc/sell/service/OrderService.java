package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description: 订单接口
 * @author: zr
 * @date: 2020/4/3 18:20
 */
public interface OrderService {

    /**
     * 新建订单
     * @param orderDto  订单对象
     * @return OrderDto
     */
    OrderDto createOrder(OrderDto orderDto);

    /**
     * 查询指定订单
     * @param orderId 订单ID
     * @return 订单对象
     */
    OrderDto findOne(String orderId);

    /**
     * 查新订单列表
     * @param buyerOpenid openid
     * @param pageable 分页对象
     * @return Page
     */
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDto 取消订单对象
     * @return 取消的订单
     */
    OrderDto cancelOrder(OrderDto orderDto);

    /**
     * 完成订单
     * @param orderDto 完成订单对象
     * @return 完成的订单
     */
    OrderDto finishOrder(OrderDto orderDto);

    /**
     * 支付订单
     * @param orderDto 支付订单对象
     * @return 完成的订单
     */
    OrderDto paidOrder(OrderDto orderDto);
}
