package com.nhnacademy.shoppingmall.categories.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(int categoryId) {
        super(String.format("category already exist:%s", categoryId));
    }
}
