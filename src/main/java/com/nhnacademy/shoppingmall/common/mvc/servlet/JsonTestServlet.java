package com.nhnacademy.shoppingmall.common.mvc.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
@WebServlet(name = "jsonTestServlet", urlPatterns = {"/json"})
public class JsonTestServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DbConnectionThreadLocal.initialize();
        try {
            log.debug("jsonTestServlet called");
//            String userId = req.getHeader("userId");

            String line;
            BufferedReader br = req.getReader();
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            JSONObject data = new JSONObject(sb.toString());
            String userId = data.getString("userId");
            log.debug("userId:{}", userId);


            boolean check = userService.idCheck(userId);
            res.setContentType("application/json");
            res.getWriter().write("{\"userId\":\"" + userId + "\", \"status\":\"" + (check ? "available" : "unavailable") + "\"}");



        } catch (Exception e) {
            log.error("이미 존재하는 아이디입니다.");
            DbConnectionThreadLocal.setSqlError(true);
        } finally {
            DbConnectionThreadLocal.reset();
        }

    }
}
