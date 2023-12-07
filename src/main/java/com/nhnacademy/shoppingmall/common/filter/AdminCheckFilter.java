package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebFilter(
        filterName = "adminCheckFilter",
        urlPatterns = "/admi*"
)
@Slf4j
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        HttpSession session = req.getSession();

        // 저장된 loginUser 세션 꺼냄
        // 만약 세션이 존재하지 않다면 메인화면으로 포워딩
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null || loginUser.getUserAuth() != User.Auth.ROLE_ADMIN || session == null) {
            log.error("your not admin OR not logined :{}", session);
            res.sendError(403);
            res.setStatus(403);
            return;
        }

        chain.doFilter(req, res);

    }
}
