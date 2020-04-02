package com.imooc.sell.converter;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 对象转换类
 * @author: zr
 * @date: 2020/4/3 23:15
 */
public class OrderMasterToOrderDtoConverter  {

    /**
     * OrderMaster to OrderDto
     * @param orderMaster OrderMaster
     * @return OrderDto
     */
    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map( e ->
                convert(e)
        ).collect(Collectors.toList());
    }

}
