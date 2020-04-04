package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 商品信息Service 实现类
 * @author: zr
 * @date: 2020/4/2 21:25
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     *
     * @param productId 商品ID
     * @return
     */
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    /**
     * 查询所有在架商品列表
     * @return 在架商品
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     *
     * @param pageable 分页
     * @return 分页
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    /**
     *
     * @param productInfo 保存/修改的商品
     * @return 商品信息
     */
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    /**
     * 加库存
     *
     * @param cartDtoList 购物车对象
     */
    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if(null == productInfo){
                //判断商品不存 抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer productStock = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(productStock);
            //保存更新
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 减库存
     *
     * @param cartDtoList 购物车对象
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {

        for(CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if(null == productInfo){
                //判断商品不存 抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int result = productInfo.getProductStock() - cartDto.getProductQuantity();

            if(result < 0 ){
                //商品库存不足 抛出异常
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //设置新的库存数量
            productInfo.setProductStock(result);

            //保存更新
            productInfoRepository.save(productInfo);
        }
    }
}
