package com.nhnacademy.shoppingmall.order.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private int orderId;
    private String userId;
    private LocalDateTime orderDate;
    private LocalDateTime shipDate;
    private String address;

    public Order(String userId, LocalDateTime orderDate, LocalDateTime shipDate,String address) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.address = address;
    }

    public Order(int orderId, String userId, LocalDateTime orderDate, LocalDateTime shipDate,String address) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(userId, order.userId) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(shipDate, order.shipDate) && Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, orderDate, shipDate, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", orderDate=" + orderDate +
                ", shipDate=" + shipDate +
                ", address='" + address + '\'' +
                '}';
    }
}
