package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product getProduct(int productId) {
        Optional<Product> productOptional = productRepository.findByProductId(productId);

        return productOptional.orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        // 제품이 이미 존재한다면 예외 던짐
        int count = productRepository.countByProductId(product.getProductId());
        if (count > 0) {
            throw new ProductAlreadyExistsException(product.getProductId());
        }

        int result = productRepository.save(product);
        log.debug("saveProduct result :{}", result);
        if (result < 1) {
            throw new RuntimeException("can not saveProduct");
        }

    }

    @Override
    public void updateProduct(Product product) {
        // 제품이 존재하지 않는다면
        int count = productRepository.countByProductId(product.getProductId());
        if (count < 1) {
            throw new ProductNotFoundException(product.getProductId());
        }

        int result = productRepository.update(product);
        log.debug("updateProduct result :{}", result);
        if (result < 1) {
            throw new RuntimeException("can not updateProduct");
        }
    }

    @Override
    public void deleteProduct(int productId) {
        // 제품이 존재하지 않을때
        int count = productRepository.countByProductId(productId);
        if (count < 1) {
            throw new ProductNotFoundException(productId);
        }

        int result = productRepository.deleteByProductId(productId);
        log.debug("deleteProduct result :{}", result);
        if (result < 1) {
            throw new RuntimeException("can not deleteProduct");
        }
    }

    @Override
    public int getCount() {
        return productRepository.count();
    }

    @Override
    public int getSearchCount(String keyword) {
        if (keyword.equals("")) {
            return getCount();
        }
        return productRepository.searchCount(keyword);
    }


    @Override
    public List<Product> getCurrentPageList(int offSet, int pageSize) {
        return productRepository.getCurrentpageList(offSet, pageSize);
    }

    @Override
    public List<Product> getSearchPageList(int offSet, int pageSize, String keyword) {
        if (keyword.equals("")){
            return getCurrentPageList(offSet, pageSize);
        }
        return productRepository.getSearchpageList(offSet, pageSize, keyword);
    }
}
