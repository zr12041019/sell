package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Description: 订单主表测试类
 * @author: zr
 * @date: 2020/4/3 17:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("or123457");
        orderMaster.setBuyerOpenid("Ten1234");
        orderMaster.setBuyerAddress("东土大唐");
        orderMaster.setBuyerName("唐三藏");
        orderMaster.setBuyerPhone("0000000");
        orderMaster.setOrderAmount(new BigDecimal(81));

        OrderMaster save = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(save);

    }



    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,1);

        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid("Ten1234", request);

        Assert.assertNotNull(page);

    }
}