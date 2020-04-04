package com.imooc.sell.converter;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: OrderDto -> OrderMaster
 * @author: zr
 * @date: 2020/4/4 16:58
 */
public class OrderDtoToOrderMasterConverter {

    public static OrderMaster convert(OrderDto orderDto){
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        return orderMaster;
    }

    public static List<OrderMaster> convert(List<OrderDto> orderDtoList){
        return orderDtoList.stream().map( e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
