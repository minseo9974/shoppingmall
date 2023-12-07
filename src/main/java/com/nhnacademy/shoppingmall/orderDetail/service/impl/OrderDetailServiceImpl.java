package com.nhnacademy.shoppingmall.orderDetail.service.impl;

import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.orderDetail.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.orderDetail.service.OrderDetailService;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
    @Override
    public List<OrderDetail> getList(int orderId) {
        return orderDetailRepository.getList(orderId);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        int count = orderDetailRepository.countById(orderDetail.getOrderId(), orderDetail.getProductId());
        if (count > 0) {
            throw new RuntimeException("product already exist");
        }
        int result = orderDetailRepository.save(orderDetail);
        if (result < 1) {
            throw new RuntimeException("can not saveOrderDetail");
        }
    }

    @Override
    public void deleteAll(int orderId) {
        int result = orderDetailRepository.deleteById(orderId);
        if (result < 1) {
            throw new RuntimeException("can not delete OrderDetail");
        }
    }
}
