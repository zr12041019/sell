package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryServiceImpl;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryServiceImpl.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
       List<ProductCategory> productCategoryList = productCategoryServiceImpl.findAll();
       Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = productCategoryServiceImpl.findByCategoryTypeIn(Arrays.asList(1,2,3,5));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("好吃棒",4);
        ProductCategory productCategoryResult = productCategoryServiceImpl.save(productCategory);
        Assert.assertNotNull(productCategoryResult);
    }
}
