package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/3 17:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("Det1234");
        orderDetail.setOrderId("or123457");
        orderDetail.setProductId("sringh123");
        orderDetail.setProductName("椒麻鸡");
        orderDetail.setProductPrice(new BigDecimal(182.3));
        orderDetail.setProductIcon("http://12345.jpg");
        orderDetail.setProductQuantity(5);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("or123457");
        Assert.assertNotNull(orderDetailList);

    }
}