package com.nhnacademy.shoppingmall.controller.pay;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 주문서를 작성하는 컨트롤러
 * 배송지를 결정함
 * 포인트 결제 또한 여기서 이루어짐 (결제 성공 실패 여부)
 */
@RequestMapping(method = RequestMapping.Method.POST, value = "/pay.do")
public class PayPostController implements BaseController {
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");
        User user = (User) session.getAttribute("loginUser");

        // 총 결제 금액
        int payCost = Integer.parseInt(req.getParameter("totalCost"));
        BigDecimal totalCost = BigDecimal.valueOf(payCost);
        // 배송지
        String address = req.getParameter("selectedAddressId");

        // 유저 보유 금액
        int userPointInt = user.getUserPoint();
        BigDecimal userPoint = BigDecimal.valueOf(userPointInt);

        // 유저 보유 금액 - 총 결제 금액
        int resultPoint = 0;
        try {
            BigDecimal result = userPoint.subtract(totalCost);
            resultPoint = result.intValue();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        // 유저 보유 금액 < 총 결제 금액
        if (userPoint.compareTo(totalCost) < 0) {
            // 결제 실패시
            String msg = "PAY_ERR";
            req.setAttribute("msg", msg);
            return "/pay.do";
        }
        // 결제 성공시

        // 주문기록에 추가
        Order newOrder = new Order(id, LocalDateTime.now(), LocalDateTime.now().plusDays(2), address);
        orderService.saveOrder(newOrder);

        // 주문 Detail에 정보 추가

        // 주문이 완료되었음으로 기존 장바구니 제거

        // 수정된 유저로 세션 업데이트
        userService.updateUserPoint(id, resultPoint);
        User newUser = userService.getUser(id);
        session.setAttribute("loginUser", newUser);
        return "redirect:/order.do";


    }
}
