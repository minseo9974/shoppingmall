package com.nhnacademy.shoppingmall.controller.index;

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
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = {"/index.do"})
public class IndexController implements BaseController {

        private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // 현재 페이지
        int currentPage = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        String keyword = req.getParameter("keyword") != null ? req.getParameter("keyword") : "";

        int totalCnt = productService.getSearchCount(keyword);

        // SearchCondition을 현재 페이지와 함께 생성
        SearchCondition sc = new SearchCondition(currentPage, 9, keyword, 0);

        // Pagehandler 생성
        PageHandler ph = new PageHandler(totalCnt, sc);

        // current page의 제품 리스트 반환
        List<Product> list = productService.getSearchPageList(sc.getOffset(), sc.getPageSize(),keyword);
        req.setAttribute("list", list);
        req.setAttribute("ph", ph);


        return "shop/main/index";
    }
}