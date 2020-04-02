package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 订单主表
 * @author: zr
 * @date: 2020/4/3 17:13
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {


    /**
     * 根据买家的 openid 查询买家商品
     * @param buyerOpenid 买家id
     * @param pageable 分页
     * @return Page
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
