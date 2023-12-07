package com.nhnacademy.shoppingmall.shoppingCart.service.impl;

import com.nhnacademy.shoppingmall.join.domain.CartProduct;
import com.nhnacademy.shoppingmall.shoppingCart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingCart.repository.ShoppingCartRepository;
import com.nhnacademy.shoppingmall.shoppingCart.service.ShoppingCartService;
import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    @Override
    public List<ShoppingCart> getListById(String userId) {
        return shoppingCartRepository.getAllProductByUserId(userId);
    }

    @Override
    public void saveCart(ShoppingCart shoppingCart) {
        int count = shoppingCartRepository.countById(shoppingCart.getProductId(),shoppingCart.getUserId());
        if (count > 0) {
            throw new RuntimeException("product is already exist");
        }
        int result = shoppingCartRepository.save(shoppingCart);
        if (result < 1) {
            throw new RuntimeException("can not saveCart");
        }
    }

    @Override
    public void updateCart(ShoppingCart shoppingCart) {
        int count = shoppingCartRepository.countById(shoppingCart.getProductId(),shoppingCart.getUserId());
        if (count < 1) {
            throw new RuntimeException("cart can not found");
        }
        int result = shoppingCartRepository.updateQuantity(shoppingCart);
        if (result < 1) {
            throw new RuntimeException("can not update Cart");
        }
    }

    @Override
    public void deleteCart(String userId, int productId) {
        int count = shoppingCartRepository.countById(productId,userId);
        if (count < 1) {
            throw new RuntimeException("cart can not found");
        }

        int result = shoppingCartRepository.delete(userId, productId);
        if (result < 1) {
            throw new RuntimeException("can not delete cart");
        }
    }

    @Override
    public ShoppingCart getCart(int productId, String userId) {
        int count = shoppingCartRepository.countById(productId,userId);
        if (count < 1) {
            throw new RuntimeException("cart can not found");
        }
        return shoppingCartRepository.getCartById(productId, userId);
    }

    @Override
    public void deleteAll(String userId) {
        int result = shoppingCartRepository.deleteAll(userId);
        if (result < 1) {
            throw new RuntimeException("can not delete cart");
        }
    }

    @Override
    public List<CartProduct> getCPList(String userId) {
        return shoppingCartRepository.getCPList(userId);
    }
}
