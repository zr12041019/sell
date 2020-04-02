package com.imooc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Description: 商品信息实体类
 * @author: zr
 * @date: 2020/4/2 21:05
 */
@Entity
@Data
public class ProductInfo {

    /**
     * 商品主键
     */
    @Id
    private String productId;

    /**
     * 商品名字
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品小图
     */
    private String productIcon;

    /**
     * 商品状态
     *      0表示正常，1表示下架
     */
    private Integer productStatus;

    /**
     * 类目编号
     */
    private Integer categoryType;


    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }

    public ProductInfo() {
    }
}
