<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="list-group">
                <a href="<c:url value="/admin/category.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-grid"></i> 카테고리 관리
                </a>
                <a href="<c:url value="/admin/product.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-box"></i> 상품 관리
                </a>
                <a href="<c:url value="/admin/user.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-person"></i> 일반회원 관리
                </a>
                <a href="<c:url value="/admin/admin.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-shield-check"></i> 어드민 관리
                </a>
            </div>
        </div>
    </div>
</div>
