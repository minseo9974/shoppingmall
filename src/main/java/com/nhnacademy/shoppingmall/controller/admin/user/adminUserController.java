package com.nhnacademy.shoppingmall.controller.admin.user;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.PageHandler;
import com.nhnacademy.shoppingmall.common.page.SearchCondition;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET , value = "/admin/user.do")
public class adminUserController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int userTotalCnt = userService.getUserCount();

        // 현재 페이지
        int userCurrentPage = req.getParameter("Page") != null ? Integer.parseInt(req.getParameter("Page")) : 1;

        // SearchCondition을 현재 페이지와 함께 생성
        SearchCondition userSc = new SearchCondition(userCurrentPage, 10, "", 0);

        // Pagehandler 생성
        PageHandler userPh = new PageHandler(userTotalCnt, userSc);

        // current page의 제품 리스트 반환
        List<User> userList = userService.getUserPageList(userSc.getOffset(), userSc.getPageSize());

        req.setAttribute("userList", userList);
        req.setAttribute("userPh", userPh);

        return "admin/user/user";
    }
}
