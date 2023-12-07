package com.nhnacademy.shoppingmall.controller.mylogin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/myPage/update.do")
public class MyPageUserUpdateController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("loginUser");
        req.setAttribute("user", user);

        return "shop/user/update";
    }
}
