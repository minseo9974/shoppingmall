package com.nhnacademy.shoppingmall.orderDetail.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderDetail {
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal unitCost;

    public OrderDetail(int orderId, int productId, int quantity, BigDecimal unitCost) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDetail that = (OrderDetail) o;
        return orderId == that.orderId && productId == that.productId && quantity == that.quantity &&
                Objects.equals(unitCost, that.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, quantity, unitCost);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                '}';
    }
}
