package com.nhnacademy.shoppingmall.categories.repository;

import com.nhnacademy.shoppingmall.categories.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findByCategoryId(int categoryId);
    int save(Category category);

    int updateNameByCategoryId(String categoryName, int categoryId);

    int countByCategoryId(int categoryId);

    int count();

    List<Category> getList();
}
