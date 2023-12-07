package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;

public interface OrderService {
    Order getOrder(int orderId, String userId);

    void saveOrder(Order order);

    void deleteOrder(int orderId, String userId);

    int getCount();

    List<Order> getCurrentPageList(int offset, int pageSize);
}
