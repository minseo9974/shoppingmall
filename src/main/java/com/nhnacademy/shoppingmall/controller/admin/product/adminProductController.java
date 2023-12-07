package com.nhnacademy.shoppingmall.controller.admin.product;

import com.nhnacademy.shoppingmall.categories.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.categories.service.CategoryService;
import com.nhnacademy.shoppingmall.categories.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.common.page.PageHandler;
import com.nhnacademy.shoppingmall.common.page.SearchCondition;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/admin/product.do")
public class adminProductController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int totalCnt = productService.getCount();

        // 현재 페이지지
        int currentPage = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;

        // SearchCondition을 현재 페이지와 함께 생성
        SearchCondition sc = new SearchCondition(currentPage, 10, "", 0);

        // Pagehandler 생성
        PageHandler ph = new PageHandler(totalCnt, sc);

        // current page의 제품 리스트 반환
        List<Product> list = productService.getCurrentPageList(sc.getOffset(), sc.getPageSize());
        req.setAttribute("list", list);
        req.setAttribute("ph", ph);

        return "admin/product/product";
    }
}
