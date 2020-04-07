package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/7 15:33
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 发起用户支付
     * @param orderId orderId
     * @param returnUrl returnUrl
     * @param map  Map<String,Object>
     * @return ModelAndView
     */
    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){

        OrderDto orderDto = orderService.findOne(orderId);
        if(null == orderDto){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDto);

        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }

    /**
     * 处理微信支付异步通知
     * @param notifyData notifyData
     */
    @PostMapping("/notify")
    public ModelAndView responseNotify(@RequestBody String notifyData){

        PayResponse payResponse = payService.responseNotify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");

    }

}
