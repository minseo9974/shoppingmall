package com.nhnacademy.shoppingmall.product.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ProductRepositoryImplTest {
    ProductRepository productRepository = new ProductRepositoryImpl();
    Product testProduct;

    @BeforeEach
    void setUp() throws SQLException {
        DbConnectionThreadLocal.initialize();
        testProduct = new Product(999, 1, "test", "test-Name", "test-iamge", new BigDecimal(1), "test-descrip");
        productRepository.save(testProduct);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @Order(1)
    @DisplayName("저장: product 조회 by ProductId")
    void findByProductId() {
        Optional<Product> productOptional = productRepository.findByProductId(testProduct.getProductId());
        Assertions.assertEquals(testProduct,productOptional.get());
    }


    @Test
    @Order(2)
    @DisplayName("product 등록")
    void save() {
        Product newProduct = new Product(1000, 1, "test", "test", "test", new BigDecimal(1), "test");
        int result = productRepository.save(newProduct);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertEquals(newProduct,
                        productRepository.findByProductId(newProduct.getProductId()).get())
        );
    }

    @Test
    @Order(3)
    @DisplayName("product 중복 등록 - 제약조건 확인")
    void save_duplicate_user_id() {
        Throwable throwable = Assertions.assertThrows(RuntimeException.class,()->{
            productRepository.save(testProduct);
        });
        Assertions.assertTrue(throwable.getMessage().contains(SQLIntegrityConstraintViolationException.class.getName()));
        log.debug("errorMessage:{}", throwable.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("product 삭제")
    void deleteByProductId() {
        int result = productRepository.deleteByProductId(testProduct.getProductId());
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertFalse(productRepository.findByProductId(testProduct.getProductId()).isPresent())
        );
    }

    @Test
    @Order(5)
    @DisplayName("product 수정")
    void update() {
        testProduct.setModelName("test1");
        testProduct.setModelNumber("test2");
        testProduct.setProductImage("test3");
        testProduct.setDescription("test4");
        testProduct.setUnitCost(new BigDecimal(2));

        int result = productRepository.update(testProduct);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertEquals(testProduct,
                        productRepository.findByProductId(testProduct.getProductId()).get())
        );
    }

    @Test
    @Order(6)
    @DisplayName("product 존재여부")
    void countByProductId() {
        int result = productRepository.countByProductId(testProduct.getProductId());
        Assertions.assertEquals(1, result);
    }

    @Test
    @Order(7)
    @DisplayName("전제품 개수")
    void count() {
        int cnt = productRepository.count();
        Assertions.assertEquals(107,cnt);
    }

    @Test
    @Order(8)
    @DisplayName("검색결과 개수")
    void searchCount() {
        int cnt1 = productRepository.searchCount("진간장");
        int cnt2 = productRepository.searchCount("라면");

        Assertions.assertAll(
                ()->Assertions.assertEquals(1, cnt1),
                ()->Assertions.assertEquals(8, cnt2)
        );

    }


    @Test
    @Order(9)
    @DisplayName("전체 제품 페이지 리스트")
    void getCurrentpageList() {
        List<Product> testList = productRepository.getCurrentpageList(0, 10);
        int i = 1;
        for (Product item : testList) {
            assertEquals(i++, item.getProductId());
        }
    }

    @Test
    @Order(10)
    @DisplayName("검색어가 포함된 페이지 리스트")
    void getSearchpageList() {
        List<Product> testList = productRepository.getSearchpageList(0, 10, "라면");
        for (Product item : testList) {
            assertTrue(item.getModelName().contains("라면"));
        }
    }
}