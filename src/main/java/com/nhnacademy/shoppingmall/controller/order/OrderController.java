package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.join.domain.CartProduct;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.orderDetail.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.orderDetail.service.OrderDetailService;
import com.nhnacademy.shoppingmall.orderDetail.service.impl.OrderDetailServiceImpl;
import com.nhnacademy.shoppingmall.shoppingCart.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingCart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingCart.service.impl.ShoppingCartServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * 주문 내역 조회
 */
@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/order.do")
public class OrderController implements BaseController {
    private final ShoppingCartService cartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");

        Order order = orderService.getOrder(id);

        // 주문 Detail에 정보 추가
        List<CartProduct> cpList = cartService.getCPList(id);
        for (CartProduct cp : cpList) {
            orderDetailService.save(
                    new OrderDetail(order.getOrderId(), cp.getProductId(), cp.getQuantity(),
                            BigDecimal.valueOf(cp.getUnitCost())));
        }

        // 주문이 완료되었음으로 기존 장바구니 제거
        cartService.deleteAll(id);

        return"redirect:/order/view.do";
    }
}
