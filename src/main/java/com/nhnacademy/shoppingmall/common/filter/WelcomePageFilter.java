package com.nhnacademy.shoppingmall.common.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@WebFilter(
        filterName = "welcomePageFilter",
        urlPatterns = "/",
        initParams = {
                @WebInitParam(
                        name = "HOME",
                        value = "/index.do"
                )
        }
)
public class WelcomePageFilter extends HttpFilter {
    private String home;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.home = config.getInitParameter("HOME");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //todo#9 /요청이 오면 welcome page인 index.do redirect 합니다.
        String requestURI = req.getRequestURI();
        log.debug("getRequestURI:{}", req.getRequestURI());
        log.debug("getContextPath:{}", req.getContextPath());
        log.debug("getServletPath:{}", req.getServletPath());
        if (requestURI.equals("/")) {
            res.sendRedirect(req.getContextPath() + home);
        } else {
            chain.doFilter(req, res);
        }
    }
}
