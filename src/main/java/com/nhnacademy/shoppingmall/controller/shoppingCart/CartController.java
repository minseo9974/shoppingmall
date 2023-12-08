package com.nhnacademy.shoppingmall.controller.shoppingCart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.join.domain.CartProduct;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.shoppingCart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingCart.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingCart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingCart.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/cart.do")
public class CartController implements BaseController {
    private final ShoppingCartService shoppingCartService =
            new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productIdParam = req.getParameter("productId");
        HttpSession session = req.getSession(true);
        String userId = (String) session.getAttribute("id");
        User user = (User) session.getAttribute("loginUser");

        // 장바구니 추가 버튼을 눌렀을때
        if (productIdParam != null&& !productIdParam.isEmpty()) {
            int productId = Integer.parseInt(productIdParam);
            ShoppingCart saveCart = new ShoppingCart(productId, 1, userId);
            try {
                shoppingCartService.saveCart(saveCart);
            } catch (Exception e) {
                req.setAttribute("msg","ADD_ERR");
                log.error("이미 존재하는 상품");
                return "shop/user/myPage";
            }
        }
        // 장바구니에 상품을 띄워줄 카트 리스트
        List<CartProduct> cpList = shoppingCartService.getCPList(userId);

        // 총 결제 금액
        int totalCost = 0;
        int point = user.getUserPoint();

        for (CartProduct cp : cpList) {
            totalCost += cp.getUnitCost() * cp.getQuantity();
        }

        req.setAttribute("point", point);
        req.setAttribute("totalCost", totalCost);
        req.setAttribute("list", cpList);
        return "shop/cart/cart";
    }
}
