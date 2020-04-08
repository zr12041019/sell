package com.imooc.sell.validateForm;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/8 23:35
 */
@Data
public class ProductForm {

    /**
     * 商品主键
     */
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

//    /**
//     * 商品状态
//     *      0表示正常，1表示下架
//     */
//    private Integer productStatus;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
