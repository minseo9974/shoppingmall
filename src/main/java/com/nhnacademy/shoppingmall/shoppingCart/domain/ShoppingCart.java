package com.nhnacademy.shoppingmall.shoppingCart.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ShoppingCart {
    private int recordId;
    private int productId;
    private int quantity;
    private String userId;
    private LocalDateTime dateCreated;

    public ShoppingCart(int recordId, String userId, int quantity, int productId, LocalDateTime dateCreated) {
        this.recordId = recordId;
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
        this.dateCreated = dateCreated;
    }

    public ShoppingCart(int productId, int quantity, String userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShoppingCart that = (ShoppingCart) o;
        return recordId == that.recordId && productId == that.productId && quantity == that.quantity &&
                Objects.equals(userId, that.userId) && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, productId, quantity, userId, dateCreated);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "recordId=" + recordId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", userId='" + userId + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
