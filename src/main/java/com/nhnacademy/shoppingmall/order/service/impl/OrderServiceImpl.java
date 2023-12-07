package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public Order getOrder(int orderId, String userId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId, userId);

        return orderOptional.orElse(null);
    }

    @Override
    public void saveOrder(Order order) {
        int count = orderRepository.countById(order.getOrderId(), order.getUserId());
        if (count > 0) {
            throw new RuntimeException("Order already exist");
        }
        int result = orderRepository.save(order);
        if (result < 1) {
            throw new RuntimeException("can not saveOrder");
        }
    }

    @Override
    public void deleteOrder(int orderId, String userId) {
        int count = orderRepository.countById(orderId,userId);
        if (count < 1) {
            throw new RuntimeException("Order not found");
        }
        int result = orderRepository.deleteById(orderId,userId);
        if (result < 1) {
            throw new RuntimeException("can not deleteOrder");
        }
    }

    @Override
    public int getCount() {
        return orderRepository.count();
    }

    @Override
    public List<Order> getCurrentPageList(int offset, int pageSize) {
        return orderRepository.getCurrentPageList(offset, pageSize);
    }
}
