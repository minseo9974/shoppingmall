package com.nhnacademy.shoppingmall.join.domain;

import java.math.BigDecimal;

public class CartProduct {
    private String userId;
    private int productId;
    private String productName;
    private int Quantity;
    private int unitCost;

    public CartProduct(String userId, int productId, String productName, int quantity, int unitCost) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        Quantity = quantity;
        this.unitCost = unitCost;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }
}
