<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">

    <div class="list-group">

        <ul class="list-group">
            <li class="list-group-item">
                <a href="<c:url value="/admin/category.do"/> " class="list-group-item list-group-item-action">카테고리
                    관리</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/admin/product.do"/>  " class="list-group-item list-group-item-action">상품 관리</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/admin/user.do"/>" class="list-group-item list-group-item-action">일반회원 관리</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/admin/admin.do"/>" class="list-group-item list-group-item-action">어드민 관리</a>
            </li>

        </ul>
    </div>
</div>