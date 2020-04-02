package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @Description: 商品类目Service层
 * @author: zr
 * @date: 2020/4/2 21:05
 */
public interface ProductCategoryService {

    /**
     * 按照 id查找类目
     * @param id 类目id
     * @return 类目
     */
    ProductCategory findOne(Integer id);

    /**
     * 查找所有类目
     * @return 类目集
     */
    List<ProductCategory> findAll();

    /**
     *按照ID查找所有的类目
     * @param categoryTypeList 要查找的类目ID
     * @return 查询对应ID的类目集
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 修改或者新建类目
     * @param productCategory 要保存或者修改的类目
     * @return 修改类目
     */
    ProductCategory save(ProductCategory productCategory);
}
