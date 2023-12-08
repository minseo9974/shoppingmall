package com.nhnacademy.shoppingmall.product.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
    @DisplayName("save product - already exist product")
    void saveProduct_exist() {
        Mockito.when(productRepository.countByProductId(anyInt())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(ProductAlreadyExistsException.class,
                () -> productService.saveProduct(testProduct));
        log.debug("error:{}", throwable.getMessage());
    }

    @Test
    @DisplayName("update product")
    void updateProduct() {
        Mockito.when(productRepository.countByProductId(anyInt())).thenReturn(1);
        Mockito.when(productRepository.update(testProduct)).thenReturn(1);

        productService.updateProduct(testProduct);

        Mockito.verify(productRepository, Mockito.times(1)).update(any());
        Mockito.verify(productRepository, Mockito.times(1)).countByProductId(anyInt());
    }

    @Test
    @DisplayName("delete product")
    void deleteProduct() {
        Mockito.when(productRepository.deleteByProductId(anyInt())).thenReturn(1);
        Mockito.when(productRepository.countByProductId(anyInt())).thenReturn(1);

        productService.deleteProduct(testProduct.getProductId());

        Mockito.verify(productRepository, Mockito.times(1)).deleteByProductId(anyInt());
        Mockito.verify(productRepository, Mockito.times(1)).countByProductId(anyInt());
    }

    @Test
    @DisplayName("count product")
    void getCount() {
        Mockito.when(productRepository.count()).thenReturn(5);
        int result = productService.getCount();
        Assertions.assertEquals(5, result);
    }

    @Test
    @DisplayName("search count")
    void getSearchCount() {
        String keyword = "라면";
        Mockito.when(productRepository.searchCount(keyword)).thenReturn(8);
        int result = productService.getSearchCount(keyword);
        Assertions.assertEquals(8, result);
    }
}