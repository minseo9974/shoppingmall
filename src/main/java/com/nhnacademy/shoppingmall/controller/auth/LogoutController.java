package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.cookie.CookieUtil;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/logout.do")
public class LogoutController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-3 로그아웃 구현
        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session)) {
            session.invalidate();
        }
        CookieUtil cookieUtil = new CookieUtil(req);
        Cookie cookie = cookieUtil.getCookie("JSESSIONID");

        if (Objects.nonNull(cookie)) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        return "redirect:/index.do";
    }
}
