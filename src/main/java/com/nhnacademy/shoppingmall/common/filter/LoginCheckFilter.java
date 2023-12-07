package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "loginCheckFilter",
        urlPatterns = {"/myPage*","/order*","/pay*","/cart*","/history*"}
)
@Slf4j
public class LoginCheckFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        HttpSession session = req.getSession(false);
        // session null일 경우 로그인 페이지 이동
        if (session == null) {
            String msg = "LOGIN_ERR";
            req.setAttribute("msg", msg);
            RequestDispatcher rd = req.getRequestDispatcher("shop/login/login_form.jsp");
            rd.forward(req, res);
            return;
        }

        // 세션에 저장되어있는 loginUser 꺼냄
        User loginUser = (User) session.getAttribute("loginUser");
        String userId = (String) session.getAttribute("id");
        log.debug("loginCheck Filter working :{},{}", loginUser.getUserId(), userId);

        // 세션에 저장된 userId와 loginUser가 같으면 다음 필터 넘김
        if (userId != null && userId.equals(loginUser.getUserId())) {
            chain.doFilter(req, res);
        }else{
            // 다르면 로그인 페이지 이동
            RequestDispatcher rd = req.getRequestDispatcher("shop/login/login_form.jsp");
            rd.forward(req, res);
        }
    }
}