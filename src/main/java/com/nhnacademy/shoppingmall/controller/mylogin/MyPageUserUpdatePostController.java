package com.nhnacademy.shoppingmall.controller.mylogin;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/myPage/update.do")
public class MyPageUserUpdatePostController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String birth = req.getParameter("birth");
        String auth = req.getParameter("auth");
        int point = Integer.parseInt(req.getParameter("point"));

        String createdAt = req.getParameter("createdAt");
        LocalDateTime created = LocalDateTime.parse(createdAt, formatter);

        String lateLogin = req.getParameter("latestLoginAt");
        LocalDateTime late = LocalDateTime.parse(lateLogin, formatter);
        User newUser = new User(id, name, password, birth, User.Auth.valueOf(auth), point, created, late);
        userService.updateUser(newUser);

        HttpSession session = req.getSession(true);
        session.setAttribute("loginUser", newUser);

        return "redirect:/myPage.do";
    }
}
