package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.time.LocalDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
//        ServletContext context = sce.getServletContext();
        DbConnectionThreadLocal.initialize();

        User admin = new User("admin", "MINSEO", "12345", "19990704", User.Auth.ROLE_ADMIN, 1_000_000,
                LocalDateTime.now(), null);
        User user = new User("user", "user", "12345", "19990101", User.Auth.ROLE_USER, 1_000_000, LocalDateTime.now(),
                null);
        log.debug("admin :{} , user :{}", admin, user);

            // 어드민, 유저 생성
        try{
            userService.saveUser(admin);
        } catch (Exception e) {
            log.debug("already user exist");
        }
        try{
            userService.saveUser(user);
        } catch (Exception e) {
            log.debug("already user exist");
        }

        // application scope에서 userService 객체에 접근할 수 있도록 구현
//        context.setAttribute("userService", userService);

        DbConnectionThreadLocal.setSqlError(false);
        DbConnectionThreadLocal.reset();
    }
}
