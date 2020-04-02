package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: 商品信息 Service层
 * @author: zr
 * @date: 2020/4/2 21:05
 */
public interface ProductInfoService {

    /**
     * 按ID查询商品
     * @param productId 商品ID
     * @return 商品
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return 在家商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查找商品
     * @param pageable 分页
     * @return 一页数据集
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存商品
     * @param productInfo 保存/修改的商品
     * @return 保存的商品
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDtoList 购物车对象
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     * @param cartDtoList 购物车对象
     */
    void decreaseStock(List<CartDto> cartDtoList);

}
