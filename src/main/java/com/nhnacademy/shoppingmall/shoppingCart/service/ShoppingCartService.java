package com.nhnacademy.shoppingmall.shoppingCart.service;

import com.nhnacademy.shoppingmall.shoppingCart.domain.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getListById(String userId);

    void saveCart(ShoppingCart shoppingCart);

    void updateCart(ShoppingCart shoppingCart);

    void deleteCart(String userId, int productId);

    ShoppingCart getCart(int productId, String userId);

    void deleteAll(String userId);
}
