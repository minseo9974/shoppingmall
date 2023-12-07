package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.List;

public interface ProductService {
    Product getProduct(int productId);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    int getCount();

    int getSearchCount(String keyword);

    List<Product> getList(int productId);

    List<Product> getCurrentPageList(int offSet, int pageSize);
    List<Product> getSearchPageList(int offSet, int pageSize,String keyword);
}
