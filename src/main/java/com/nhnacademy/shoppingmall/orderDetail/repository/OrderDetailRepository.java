package com.nhnacademy.shoppingmall.orderDetail.repository;

import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    List<OrderDetail> getList(int orderId);

    int save(OrderDetail orderDetail);

    int deleteById(int orderId);

    int countById(int orderId, int productId);


}
