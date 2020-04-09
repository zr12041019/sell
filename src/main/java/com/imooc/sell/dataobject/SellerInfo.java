package com.imooc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 14:51
 */
@Data
@Entity
public class SellerInfo {

    /**
     * ID
     */
    @Id
    private String sellerId;

    /**
     *用户姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户微信ID
     */
    private String openid;
}
