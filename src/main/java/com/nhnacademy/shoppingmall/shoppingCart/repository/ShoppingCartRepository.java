package com.nhnacademy.shoppingmall.shoppingCart.repository;

import com.nhnacademy.shoppingmall.shoppingCart.domain.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    // 유저가 담은 품목을 모두 가져온다.
    List<ShoppingCart> getAllProductByUserId(String userId);

    // 해당 제품이 존재하는지 확인 (중복 방지)
    int countById(int productId,String userId);

    ShoppingCart getCartById(int productId, String userId);

    // 쇼핑카트에 품목 추가
    int save(ShoppingCart shoppingCart);

    // 품목 수량 변경
    int updateQuantity(ShoppingCart shoppingCart);

    // 장바구니에서 삭제 유저아이디와 제품아이디가 일치해야함
    int delete(String userId, int productId);

    // 장바구니 주문 완료후 삭제
    int deleteAll(String userId);

}
