package com.imooc.sell.service.impl;


import com.imooc.sell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 15:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoServiceImpl sellerInfoServiceImpl;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = sellerInfoServiceImpl.findSellerInfoByOpenid("wx123456");
        Assert.assertEquals("wx123456",sellerInfo.getOpenid());
    }
}