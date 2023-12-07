package com.nhnacademy.shoppingmall.controller.admin.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.math.BigDecimal;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/admin/product/update.do")
public class adminProductUpdatePostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String number = req.getParameter("number");
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        BigDecimal cost = BigDecimal.valueOf(Long.parseLong(req.getParameter("cost")));
        String text = req.getParameter("description");

        if (Objects.isNull(id) || Objects.isNull(categoryId)|| Objects.isNull(number)|| Objects.isNull(name)|| Objects.isNull(image)|| Objects.isNull(cost)|| Objects.isNull(text)) {
            log.error("parameter can not be null");
            return "redirect:/admin/product.do";
        }
        Product newProduct = new Product(id, categoryId, number, name, image, cost, text);
        productService.updateProduct(newProduct);
        return "redirect:/admin/product/view.do?id=" + id;
    }
}
