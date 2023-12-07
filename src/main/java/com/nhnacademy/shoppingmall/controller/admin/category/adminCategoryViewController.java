package com.nhnacademy.shoppingmall.controller.admin.category;

import com.nhnacademy.shoppingmall.categories.domain.Category;
import com.nhnacademy.shoppingmall.categories.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.categories.service.CategoryService;
import com.nhnacademy.shoppingmall.categories.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET,value = "/admin/category/view.do")
public class adminCategoryViewController implements BaseController {
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        if (Objects.isNull(id)) {
            throw new RuntimeException("category id can not be bull");
        }

        Category category = categoryService.getCategory(id);

        req.setAttribute("category", category);

        return "admin/category/view";
    }
}
