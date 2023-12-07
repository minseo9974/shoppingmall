package com.nhnacademy.shoppingmall.categories.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(int categoryId) {
        super(String.format("category not found:" + categoryId));
    }
}
