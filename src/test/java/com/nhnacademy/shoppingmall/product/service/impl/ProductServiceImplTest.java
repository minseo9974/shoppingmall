package com.nhnacademy.shoppingmall.product.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    ProductService productService = new ProductServiceImpl(productRepository);
    Product testProduct = new Product(999, 1, "test", "test-Name", "test-iamge", new BigDecimal(1), "test-descrip");


    @Test
    @DisplayName("getProduct")
    void getProduct() {
        Mockito.when(productRepository.findByProductId(anyInt())).thenReturn(Optional.of(testProduct));
        productService.getProduct(testProduct.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).findByProductId(anyInt());
    }

    @Test
    @DisplayName("save product")
    void saveProduct() {
        Mockito.when(productRepository.countByProductId(anyInt())).thenReturn(0);
        Mockito.when(productRepository.save(any())).thenReturn(1);
        productService.saveProduct(testProduct);
        Mockito.verify(productRepository, Mockito.times(1)).countByProductId(anyInt());
        Mockito.verify(productRepository, Mockito.times(1)).save(any());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getCount() {
    }

    @Test
    void getSearchCount() {
    }

    @Test
    void getCurrentPageList() {
    }

    @Test
    void getSearchPageList() {
    }
}