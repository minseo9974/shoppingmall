package com.nhnacademy.shoppingmall.controller.register;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/signup.do")
public class RegisterPostController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("user_id");
        String name = req.getParameter("user_name");
        String password = req.getParameter("user_password");
        String birth = req.getParameter("user_birth");
        String auth = req.getParameter("user_auth");
        int point = 1_000_000;
        LocalDateTime now = LocalDateTime.now();

        if (Objects.isNull(id)||Objects.isNull(name)||Objects.isNull(password)||Objects.isNull(birth)||Objects.isNull(auth)){
            log.error("input is null");
            return "redirect:/signup.do";
        }
        if (id.isEmpty() || name.isEmpty() || password.isEmpty() || birth.isEmpty() || auth.isEmpty()) {
            log.error("input is empty");
            return "redirect:/signup.do";
        }

        try {
            // 존재하지 않는 아이디는 등록
            userService.saveUser(
                    new User(id, name, password, birth, User.Auth.valueOf(auth), point, now, null)
            );


        } catch (Exception e) {
            log.debug("register saveUser error:{}", e);
            // 이미 존재하는 아이디
            return "redirect:/signup.do";
        }

        return "/shop/login/login_form";
    }

}
