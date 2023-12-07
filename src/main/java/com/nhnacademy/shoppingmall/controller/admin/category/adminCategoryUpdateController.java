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

@RequestMapping(method = RequestMapping.Method.GET , value = "/admin/category/update.do")
public class adminCategoryUpdateController implements BaseController {
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryService.getCategory(id);
        if (Objects.isNull(category)) {
            throw new RuntimeException("category not found :" + id);
        }
        req.setAttribute("category", category);
        return "admin/category/register";
    }
}
