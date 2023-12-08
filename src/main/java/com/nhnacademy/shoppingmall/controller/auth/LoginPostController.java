package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");

        try {
            userService.getDayPoint(userId, userPassword);
            User user = userService.doLogin(userId, userPassword);
            if (Objects.nonNull(user)) {

                HttpSession session = req.getSession(true);

                // session에 id 저장
                session.setAttribute("id", userId);
                session.setAttribute("auth",user.getUserAuth().toString());

                // session에 유저객체 저장
                // todo user객체 세션 전송 지양 하고 다시 필요한 요소만 보내기
                session.setAttribute("loginUser", user);

                // session 60분 설정
                session.setMaxInactiveInterval(60 * 60);


                return "redirect:/index.do";
            }
        } catch (Exception e) {
            log.debug("LoginPost something wrong:{}", e);
        }
        // 로그인 실패
        return "shop/login/login_form";


    }
}
