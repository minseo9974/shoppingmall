package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.PageHandler;
import com.nhnacademy.shoppingmall.common.page.SearchCondition;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 주문 건마다 상세 주문 명세서를 보여주는 view
 */
@RequestMapping(method = RequestMapping.Method.GET, value = "/order/view.do")
public class OrderViewController implements BaseController {
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");

        User newUser = userService.getUser(id);
        session.setAttribute("loginUser", newUser);

        // 주문 건수를 보여주기 위한 페이리스트 전송
        int totalCnt = orderService.getCount(id);

        int currentPage = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;

        // SearchCondition을 현재 페이지와 함께 생성
        SearchCondition sc = new SearchCondition(currentPage, 10, "", 0);

        // Pagehandler 생성
        PageHandler ph = new PageHandler(totalCnt, sc);

        // current page의 오더 리스트 반환
        List<Order> list = orderService.getCurrentPageList(sc.getOffset(), sc.getPageSize(),id);
        req.setAttribute("list", list);
        req.setAttribute("ph", ph);

        return "shop/order/order";
    }
}