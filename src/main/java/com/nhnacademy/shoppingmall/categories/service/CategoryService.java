package com.nhnacademy.shoppingmall.categories.service;

import com.nhnacademy.shoppingmall.categories.domain.Category;
import java.util.List;

public interface CategoryService {
    Category getCategory(int categoryId);

    void saveCategory(Category category);

    void updateCategory(Category category);

    int count();

    List<Category> getList();
}
