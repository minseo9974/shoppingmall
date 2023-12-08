package com.nhnacademy.shoppingmall.controller.pay;

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
import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.pointHistory.repository.impl.PointHistoryRepositoryImpl;
import com.nhnacademy.shoppingmall.pointHistory.service.PointHistoryService;
import com.nhnacademy.shoppingmall.pointHistory.service.impl.PointHistoryServiceImpl;
import com.nhnacademy.shoppingmall.shoppingCart.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingCart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingCart.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.impl.PointChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * 주문서를 작성하는 컨트롤러
 * 배송지를 결정함
 * 포인트 결제/적립 또한 여기서 이루어짐 (결제 성공 실패 여부)
 */
@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/pay.do")
public class PayPostController implements BaseController {
    private final ShoppingCartService cartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final PointHistoryService pointHistoryService =
            new PointHistoryServiceImpl(new PointHistoryRepositoryImpl());

    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");
        User user = (User) session.getAttribute("loginUser");

        // 총 결제 금액
        int payCost = Integer.parseInt(req.getParameter("totalCost"));
        BigDecimal totalCost = new BigDecimal(req.getParameter("totalCost"));
        // 배송지
        String address = req.getParameter("deliveryAddress");
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
            return "/shop/user/myPage";
        }
        // 결제 성공시
        // 주문기록에 추가
        Order newOrder = new Order(id, LocalDateTime.now(), LocalDateTime.now().plusDays(2), address);
        int orderKey = orderService.saveOrder(newOrder);

        // 주문 Detail에 정보 추가
        List<CartProduct> cpList = cartService.getCPList(id);
        for (CartProduct cp : cpList) {
            orderDetailService.save(
                    new OrderDetail(orderKey, cp.getProductId(), cp.getQuantity(),
                            BigDecimal.valueOf(cp.getUnitCost())));
        }

        // 수정된 유저로 세션 업데이트
        userService.updateUserPoint(id, resultPoint);
        User newUser = userService.getUser(id);
        session.setAttribute("loginUser", newUser);

        // 포인트 히스토리 내역 추가
        PointHistory pointHistory = new PointHistory(id, "출금", totalCost, LocalDateTime.now());
        pointHistoryService.save(pointHistory);

        // 포인트 적립을 위한 새로운 트랜잭션 실행
        ServletContext ctx = req.getServletContext();
        RequestChannel requestChannel = (RequestChannel) ctx.getAttribute("requestChannel");

        try {
            requestChannel.addRequest(new PointChannelRequest(payCost,resultPoint, id));
        } catch (InterruptedException e) {
            log.error("적립 실패:{}", e);
            throw new RuntimeException("적립에 실패 하였습니다.");
        }

        // 주문이 완료되었음으로 기존 장바구니 제거
        cartService.deleteAll(id);

        return"redirect:/order/view.do";

    }
}
