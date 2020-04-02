package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/3 17:18
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单id查询订单
     * @param orderId 订单详情 id
     * @return 订单集
     */
    List<OrderDetail> findByOrderId(String orderId);
}
