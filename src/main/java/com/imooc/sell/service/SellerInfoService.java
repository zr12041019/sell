package com.imooc.sell.service;

import com.imooc.sell.dataobject.SellerInfo;

/**
 * @Description: 卖家端用户
 * @author: zr
 * @date: 2020/4/9 15:06
 */
public interface SellerInfoService {

    /**
     * 通过openid查询卖家端用户信息
     * @param openid openid
     * @return SellerInfo
     */
    SellerInfo findSellerInfoByOpenid(String openid);

    SellerInfo findSellerInfoByUsernameAndPwd(String username,String password);

    SellerInfo save(SellerInfo sellerInfo);
}
