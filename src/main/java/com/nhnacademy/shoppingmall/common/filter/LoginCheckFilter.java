package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebFilter(
        filterName = "loginCheckFilter",
        urlPatterns = {"/myPage*", "/order*", "/pay*", "/cart*", "/history*"}
)
@Slf4j
public class LoginCheckFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        HttpSession session = req.getSession(false);

        // session null일 경우 로그인 페이지 이동
        if (session == null || session.getAttribute("id") == null || !session.getAttribute("id").equals(((User) session.getAttribute("loginUser")).getUserId())) {
            String msg = "LOGIN_ERR";
            req.setAttribute("msg", msg);
            res.sendRedirect("/login.do");
            return;
        }

        chain.doFilter(req, res);
    }
}