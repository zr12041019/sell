package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @author: zr
 * @date: 2020/4/3 21:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void createOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("猪八戒");
        orderDto.setBuyerAddress("高老庄");
        orderDto.setBuyerPhone("1695368222");
        orderDto.setBuyerOpenid("1001016");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail od1 = new OrderDetail();
        od1.setProductId("sringh123");
        od1.setProductQuantity(2);
        orderDetailList.add(od1);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("sringh1234");
        od2.setProductQuantity(3);
        orderDetailList.add(od2);

        orderDto.setOrderDetailList(orderDetailList);

        OrderDto orderResult = orderServiceImpl.createOrder(orderDto);

        Assert.assertNotNull(orderResult);
    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderServiceImpl.findOne("1585924914588808816");
        Assert.assertNotNull(orderDto);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderDto> orderDtoPage = orderServiceImpl.findList("1001014", pageRequest);
        Assert.assertNotNull(orderDtoPage);
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void finishOrder() {
    }

    @Test
    public void paidOrder() {
    }
}