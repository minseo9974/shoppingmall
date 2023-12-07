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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/myPage/address/update.do")
public class MyPageAddressUpdateController implements BaseController {
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int addressId = Integer.parseInt(req.getParameter("id"));
        Address address = addressService.getAddressById(addressId);

        if (Objects.isNull(address)) {
            log.error("parameter can not be null");
            return "redirect:/myPage/address.do";
        }

        req.setAttribute("address", address);
        return "shop/address/register";
    }
}
