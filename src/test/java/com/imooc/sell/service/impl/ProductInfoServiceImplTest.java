package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/2 21:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoServiceImpl;


    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoServiceImpl.findOne("sringh123");
        Assert.assertEquals("sringh123",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoServiceImpl.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> page = productInfoServiceImpl.findAll(pageRequest);
//        System.out.println(page.getTotalElements());
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo("sringh1234","椒麻鸡",new BigDecimal(63.5),100,"专门为高富帅定制的鸡","http://123.jpg",0,2);
        ProductInfo productInfoRe = productInfoServiceImpl.save(productInfo);
        Assert.assertNotNull(productInfoRe);
    }

    @Test
    public void onSale(){
        ProductInfo productInfo = productInfoServiceImpl.onSale("shangpin100002");
        Assert.assertEquals(ProductStatusEnum.UP,productInfo.getProductStatusEnum());
    }

    @Test
    public void offSale(){
        ProductInfo productInfo = productInfoServiceImpl.offSale("shangpin100002");
        Assert.assertEquals(ProductStatusEnum.DOWN,productInfo.getProductStatusEnum());
    }
}