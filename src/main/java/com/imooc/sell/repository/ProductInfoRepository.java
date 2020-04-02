package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/2 21:05
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 查询所有上架商品
     * @param productStatus 商品状态
     * @return 上架商品集
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
