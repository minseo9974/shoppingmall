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

@RequestMapping(method = RequestMapping.Method.POST,value = "/admin/category/register.do")
public class adminCategoryRegisterPostController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        if (Objects.isNull(id) || Objects.isNull(name)) {
            throw new RuntimeException("id&name can not be null");
        }
        Category category = new Category(id, name);
        categoryService.saveCategory(category);
        return "redirect:/admin/category/view.do?id=" + id;
    }
}
