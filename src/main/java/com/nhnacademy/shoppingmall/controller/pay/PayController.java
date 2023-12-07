package com.nhnacademy.shoppingmall.controller.pay;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
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

/**
 * 카트 jsp에서 주문 클릭시 장바구니 정보와 함께 결제 창으로 이동
 */
@RequestMapping(method = RequestMapping.Method.GET, value = "/pay.do")
public class PayController implements BaseController {
    private final ShoppingCartService cartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");
        User user = (User) session.getAttribute("loginUser");
        // 총 결제 금액
        BigDecimal totalCost = new BigDecimal(0);

        // 결제창에 상품을 띄워줄 카트 리스트
        List<ShoppingCart> cartList = cartService.getListById(id);
        List<Address> addressList = addressService.getList(id);
        List<Product> productList = new ArrayList<>();
        for (ShoppingCart carts : cartList) {
            // 물품 갯수와 가격을 총 합친 가격
            totalCost = totalCost.add(BigDecimal.valueOf(carts.getQuantity()).multiply(productService.getProduct(carts.getProductId()).getUnitCost()));
            productList.add(productService.getProduct(carts.getProductId()));
        }
        int point = user.getUserPoint();

        req.setAttribute("point", point);
        req.setAttribute("totalCost", totalCost);
        req.setAttribute("list", cartList);
        req.setAttribute("productList", productList);
        req.setAttribute("addressList", addressList);

        return "shop/pay/pay";
    }
}
