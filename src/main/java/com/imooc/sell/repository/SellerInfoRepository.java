package com.imooc.sell.repository;

import com.imooc.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 14:54
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    /**
     * 根据用户的openid 查找用户
     * @param openid openid
     * @return SellerInfo
     */
    SellerInfo findByOpenid(String openid);

    SellerInfo findByUsernameAndPassword(String username,String password);
}
