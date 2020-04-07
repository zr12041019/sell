package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Description: 支付
 * @author: zr
 * @date: 2020/4/7 15:39
 */
public interface PayService {

    /**
     * 发起支付
     * @param orderDto OrderDto
     * @return PayResponse
     */
    PayResponse create(OrderDto orderDto);

    /**
     * 处理微信支付异步通知
     * @param notifyData notifyData
     * @return PayResponse
     */
    PayResponse responseNotify(String notifyData);

    /**
     * 退款
     * @param orderDto OrderDto
     */
    RefundResponse refund(OrderDto orderDto);
}
