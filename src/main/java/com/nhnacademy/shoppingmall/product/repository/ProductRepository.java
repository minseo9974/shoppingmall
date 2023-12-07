package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findByProductId(int productId);

    int save(Product product);

    int update(Product product);

    int deleteByProductId(int productId);

    int countByProductId(int productId);

    int count();
    int searchCount(String keyword);

    List<Product> getList(int productId);

    List<Product> getCurrentpageList(int offSet, int pageSize);
    List<Product> getSearchpageList(int offSet, int pageSize,String keyword);

}
