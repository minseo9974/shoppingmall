package com.nhnacademy.shoppingmall.controller.admin.history;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.PageHandler;
import com.nhnacademy.shoppingmall.common.page.SearchCondition;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.pointHistory.repository.impl.PointHistoryRepositoryImpl;
import com.nhnacademy.shoppingmall.pointHistory.service.PointHistoryService;
import com.nhnacademy.shoppingmall.pointHistory.service.impl.PointHistoryServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/history.do")

public class HistoryController implements BaseController {
    private final PointHistoryService pointHistoryService =
            new PointHistoryServiceImpl(new PointHistoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");

        // 주문 건수를 보여주기 위한 페이리스트 전송
        int totalCnt = pointHistoryService.getCount(id);

        int currentPage = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;

        // SearchCondition을 현재 페이지와 함께 생성
        SearchCondition sc = new SearchCondition(currentPage, 10, "", 0);

        // Pagehandler 생성
        PageHandler ph = new PageHandler(totalCnt, sc);

        // current page의 오더 리스트 반환
        List<PointHistory> list = pointHistoryService.getCurrentPageList(sc.getOffset(), sc.getPageSize(),id);
        req.setAttribute("list", list);
        req.setAttribute("ph", ph);

        return "shop/history/point";


    }
}
