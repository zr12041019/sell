package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMasterToOrderDtoConverter;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 订单实现类
 * @author: zr
 * @date: 2020/4/3 18:29
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    /**
     * 新建订单
     *
     * @param orderDto 订单对象
     * @return OrderDto
     */
    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {

        //订单主键
        String orderId = KeyUtil.getUniqueKey();

        //定义商品总金额 并初始化为o
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 1. 查询商品（数量 价格）
        //遍历订单详情信息
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()){
            //查询商品
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            //判断商品是否存在
            if(productInfo == null){
                //不存在 抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2. 计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            // 3. 订单详情持久化 前端的数据 只有商品id 和商品数目 因此要构造订单详情信息
            //把 productInfo 的信息对应 copy -> orderDetail
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetailRepository.save(orderDetail);
        }


        // 4. 订单主表 持久化
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        // 5.扣库存
        List<CartDto> cartDtoList =
                orderDto.getOrderDetailList().stream().map( e ->
                        new CartDto(e.getProductId(),e.getProductQuantity())
                ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }

    /**
     * 查询单个订单
     *
     * @param orderId 订单ID
     * @return OrderDto
     */
    @Override
    public OrderDto findOne(String orderId) {
        //订单主表
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(null == orderMaster){
            //订单不存在
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //订单详情表
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(null == orderDetailList){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }

    /**
     * 查新订单列表
     *
     * @param buyerOpenid openid
     * @param pageable    分页对象
     * @return Page
     */
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMastersPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMasterToOrderDtoConverter.convert(orderMastersPage.getContent());
        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMastersPage.getTotalElements());
    }

    /**
     * 取消订单
     *
     * @param orderDto 取消订单对象
     * @return 取消的订单
     */
    @Override
    public OrderDto cancelOrder(OrderDto orderDto) {
        return null;
    }

    /**
     * 完成订单
     *
     * @param orderDto 完成订单对象
     * @return 完成的订单
     */
    @Override
    public OrderDto finishOrder(OrderDto orderDto) {
        return null;
    }

    /**
     * 支付订单
     *
     * @param orderDto 支付订单对象
     * @return 完成的订单
     */
    @Override
    public OrderDto paidOrder(OrderDto orderDto) {
        return null;
    }
}
