package com.nhnacademy.shoppingmall.controller.pay;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 주문서를 작성하는 컨트롤러
 * 배송지를 결정함
 * 포인트 결제 또한 여기서 이루어짐 (결제 성공 실패 여부)
 */
@RequestMapping(method = RequestMapping.Method.POST, value = "/pay.do")
public class PayPostController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        if (false) {
            // 결제 실패시
            return "redirect:/cart.do";
        }

        // 결제 성공시
        return "redirect:/order.do";
    }
}
