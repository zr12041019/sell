package com.imooc.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.validateForm.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description: OrderFormToOrderDto
 * @author: zr
 * @date: 2020/4/4 20:30
 */
@Slf4j
public class OrderFormToOrderDtoConverter {

    public static OrderDto converter(OrderForm orderForm){

        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        //购物车
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch(Exception e) {
            log.error("【对象转换】错误 , JsonStr = {}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}
