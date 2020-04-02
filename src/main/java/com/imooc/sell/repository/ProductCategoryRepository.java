package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/2 21:05
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    /**
     *按照商品类型查找商品类目
     * @param categoryTypeList 按照商品类型查询类目
     * @return 商品类目
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
