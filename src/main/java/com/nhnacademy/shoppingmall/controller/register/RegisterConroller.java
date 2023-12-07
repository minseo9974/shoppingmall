package com.nhnacademy.shoppingmall.controller.register;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/signup.do")
public class RegisterConroller implements BaseController {

    /**
     * 회원가입 페이지 반환
     * @param req
     * @param resp
     * @return
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        return "shop/login/register_form";
    }
}
