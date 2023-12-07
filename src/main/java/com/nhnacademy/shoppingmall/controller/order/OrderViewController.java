package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 주문 건마다 상세 주문 명세서를 보여주는 view
 */
@RequestMapping( method = RequestMapping.Method.GET, value = "/order/view.do")
public class OrderViewController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "shop/user/orderDetail";
    }
}