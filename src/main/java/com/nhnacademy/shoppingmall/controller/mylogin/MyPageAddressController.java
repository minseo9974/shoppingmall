package com.nhnacademy.shoppingmall.controller.mylogin;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET,value = "/myPage/address.do")
public class MyPageAddressController implements BaseController {
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        String id = (String) session.getAttribute("id");
        // 저장된 주소지 리스트를 넘겨준다
        List<Address> list = addressService.getList(id);
        req.setAttribute("list", list);

        return "shop/address/address";
    }
}
