package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.utils.JsonUtil;
import com.imooc.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/7 15:40
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDto orderDto) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 发起支付, request = {}" , JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 发起支付, response = {}" , JsonUtil.toJson(payResponse));
        return payResponse;
    }

    /**
     *
     * @param notifyData notifyData
     * @return
     */

    @Override
    public PayResponse responseNotify(String notifyData) {
        //1.认证微信签名 bestPayService 已经认证
        //2.微信支付状态 bestPayService 已经认证
        //3.认证支付金额
        //4.支付人（下单人 == 支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知, payResponse = {}", JsonUtil.toJson(payResponse));

        //修改订单的支付状态
        OrderDto orderDto = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if(null == orderDto){
            log.error("【微信支付】异步通知， 订单不存在， orderId = {}" ,payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是都相同
        if(! MathUtil.equals(payResponse.getOrderAmount(),orderDto.getOrderAmount().doubleValue())){
            log.error("【微信支付】异步通知，订单金额不一致，orderId = {} ， 微信支付金额 = {}，订单实际金额 = {}" ,orderDto.getOrderId(),payResponse.getOrderAmount(),orderDto.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改支付状态
        orderService.paidOrder(orderDto);
        return payResponse;
    }

    /**
     * 退款
     *
     * @param orderDto OrderDto
     */
    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest refundRequest = new RefundRequest();

        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信退款】refundRequest = {} " , JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 refundResponse = {}" , JsonUtil.toJson(refundResponse));
        return refundResponse;
    }


}
