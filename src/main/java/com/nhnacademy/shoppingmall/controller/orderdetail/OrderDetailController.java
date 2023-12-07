package com.nhnacademy.shoppingmall.controller.orderdetail;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.orderDetail.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.orderDetail.service.OrderDetailService;
import com.nhnacademy.shoppingmall.orderDetail.service.impl.OrderDetailServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/orderDetail.do")
public class OrderDetailController implements BaseController {
    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int orderId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("page",req.getParameter("page"));

        List<OrderDetail> list = orderDetailService.getList(orderId);

        req.setAttribute("list", list);
        return "shop/order/orderDetail";
    }
}
