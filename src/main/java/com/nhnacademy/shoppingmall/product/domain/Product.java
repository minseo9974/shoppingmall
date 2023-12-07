package com.nhnacademy.shoppingmall.product.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int productId;
    private int categoryId;
    private String modelNumber;
    private String modelName;
    private String productImage;
    private BigDecimal unitCost;
    private String description;

    public Product(int productId, String modelName, BigDecimal unitCost) {
        this.productId = productId;
        this.modelName = modelName;
        this.unitCost = unitCost;
    }

    public Product(int productId, int categoryId, String modelNumber, String modelName, String productImage,
                   BigDecimal unitCost, String description) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImage = productImage;
        this.unitCost = unitCost;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return productId == product.productId && categoryId == product.categoryId &&
                Objects.equals(modelNumber, product.modelNumber) &&
                Objects.equals(modelName, product.modelName) &&
                Objects.equals(productImage, product.productImage) &&
                Objects.equals(unitCost, product.unitCost) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, categoryId, modelNumber, modelName, productImage, unitCost, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", modelNumber='" + modelNumber + '\'' +
                ", modelName='" + modelName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", unitCost=" + unitCost +
                ", description='" + description + '\'' +
                '}';
    }
}
