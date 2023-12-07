package com.nhnacademy.shoppingmall.controller.mylogin;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/myPage/address/update.do")
public class MyPageAddressUpdatePostController implements BaseController {
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);

        int addressId = Integer.parseInt((req.getParameter("id")));
        String userId = (String) session.getAttribute("id");
        String state = req.getParameter("state");
        String city = req.getParameter("city");
        String text = req.getParameter("text");
        String code = req.getParameter("code");

        if (Objects.isNull(addressId) || Objects.isNull(userId) || Objects.isNull(state) || Objects.isNull(city) || Objects.isNull(text) ||
                Objects.isNull(code)) {
            log.error("parameter can not be null");
            return "redirect:/myPage/address.do";
        }
        Address address = new Address(addressId, userId, state, city, text, code);
        addressService.update(address);
        return "redirect:/myPage/address.do";
    }

}
