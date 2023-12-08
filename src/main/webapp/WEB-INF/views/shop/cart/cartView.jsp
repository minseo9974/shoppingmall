<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/08
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-5">
    <h3>상품 상세 페이지</h3>
    <table class="table">
        <tbody>
        <tr>
            <th scope="row">상품 이미지</th>
            <td> <image href="${product.productImage}" width="100%" height="100%"/></td>
        </tr>
        <tr>
            <th scope="row">카테고리 이름</th>
            <td>${category.categoryName}</td>
        </tr>
        </tbody>
    </table>
    <ul class="list-inline">
        <li class="list-inline-item"><a class="btn btn-primary" href="/admin/category.do">리스트</a></li>
        <li class="list-inline-item">
            <c:url var="update_link" value="/admin/category/update.do">
                <c:param name="id" value="${category.categoryId}" />
            </c:url>
            <a class="btn btn-success" href="${update_link}">수정</a>
        </li>
    </ul>
</div>