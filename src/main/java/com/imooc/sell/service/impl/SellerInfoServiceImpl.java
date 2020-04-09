package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.repository.SellerInfoRepository;
import com.imooc.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 15:09
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 通过openid查询卖家端用户信息
     *
     * @param openid openid
     * @return SellerInfo
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

    @Override
    public SellerInfo findSellerInfoByUsernameAndPwd(String username, String password) {

        return sellerInfoRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return sellerInfoRepository.save(sellerInfo);
    }
}
