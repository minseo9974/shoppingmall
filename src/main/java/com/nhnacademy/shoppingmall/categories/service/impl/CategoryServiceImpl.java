package com.nhnacademy.shoppingmall.categories.service.impl;

import com.nhnacademy.shoppingmall.categories.domain.Category;
import com.nhnacademy.shoppingmall.categories.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.categories.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.categories.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.categories.service.CategoryService;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategory(int categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryId(categoryId);
        return categoryOptional.orElse(null);
    }

    @Override
    public void saveCategory(Category category) {
        int count = categoryRepository.countByCategoryId(category.getCategoryId());
        if (count > 0) {
            throw new CategoryAlreadyExistsException(category.getCategoryId());
        }

        int result = categoryRepository.save(category);
        if (result < 1) {
            throw new RuntimeException("can not saveCategory");
        }
    }

    @Override
    public void updateCategory(Category category) {
        int count = categoryRepository.countByCategoryId(category.getCategoryId());
        if (count < 1) {
            throw new CategoryNotFoundException(category.getCategoryId());
        }

        int result = categoryRepository.updateNameByCategoryId(category.getCategoryName(), category.getCategoryId());
        if (result < 1) {
            throw new RuntimeException("can not updateCategory");
        }

    }

    @Override
    public int count() {
        int result = categoryRepository.count();
        return result;
    }

    @Override
    public List<Category> getList() {
        return categoryRepository.getList();
    }
}
