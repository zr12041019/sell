package com.imooc.sell.repository;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 14:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("tom");
        sellerInfo.setPassword("tom123456");
        sellerInfo.setOpenid("wx123456");
        SellerInfo sellerInfo1 = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(sellerInfo1);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("wx123456");
        Assert.assertEquals("wx123456",sellerInfo.getOpenid());
    }


    @Test
    public void findByUsernameAndPassword() {
        SellerInfo sellerInfo = sellerInfoRepository.findByUsernameAndPassword("tom", "tom123456");
        Assert.assertEquals("tom",sellerInfo.getUsername());
    }
}