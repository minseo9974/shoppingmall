package com.nhnacademy.shoppingmall.orderDetail.service;

import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getList(int orderId);

    void save(OrderDetail orderDetail);

    void deleteAll(int orderId);
}
