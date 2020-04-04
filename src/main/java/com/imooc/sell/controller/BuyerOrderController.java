package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderFormToOrderDtoConverter;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVoUtil;
import com.imooc.sell.validateForm.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 买家端 controller
 * @author: zr
 * @date: 2020/4/4 20:03
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建买家订单
     * @param orderForm OrderForm
     * @param bindingResult BindingResult
     * @return ResultVO
     */
    @RequestMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确 , orderForm = {}" ,orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderFormToOrderDtoConverter.converter(orderForm);
        //判断购物车
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto orderResult = orderService.createOrder(orderDto);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 查询订单列表
     * @param openid 订单主键
     * @param page 页码
     * @param size 显示条数
     * @return ResultVO
     */
    @RequestMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }

    /**
     * 查询订单详情
     * @param openid openid
     * @param orderId orderId
     * @return ResultVO
     */
    @RequestMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        return ResultVoUtil.success( buyerService.findOrderOne(openid, orderId));
    }

    /**
     * 订单取消
     * @param openid openid
     * @param orderId orderId
     * @return ResultVO
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        buyerService.cancelOrderOne(openid, orderId);
        return ResultVoUtil.success();
    }
}
