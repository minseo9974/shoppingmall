package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    // 주문 명세서를 가져오기 위함
    Optional<Order> findById(int orderId, String userId);

    int save(Order order);

    int deleteById(int orderId, String userId);

    int countById(int orderId, String userId);

    int count();

    List<Order> getCurrentPageList(int offset, int pageSize);
}
