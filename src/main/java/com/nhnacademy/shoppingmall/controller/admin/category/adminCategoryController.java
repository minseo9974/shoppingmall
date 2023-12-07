package com.nhnacademy.shoppingmall.controller.admin.category;


import com.nhnacademy.shoppingmall.categories.domain.Category;
import com.nhnacademy.shoppingmall.categories.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.categories.service.CategoryService;
import com.nhnacademy.shoppingmall.categories.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/category.do")
public class adminCategoryController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<Category> categoryList = categoryService.getList();

        req.setAttribute("categoryList", categoryList);
        return "admin/category/category";
    }
}