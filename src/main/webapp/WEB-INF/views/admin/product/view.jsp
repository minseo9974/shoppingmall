<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/02
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>제품-조회</h3>
<table>
    <tbody>
        <tr>
            <th>제품 아이디</th>
            <td>${product.productId}</td>
        </tr>
        <tr>
            <th>카테고리 아이디</th>
            <td>${product.categoryId}</td>
        </tr>
        <tr>
            <th>제품 번호</th>
            <td>${product.modelNumber}</td>
        </tr>
        <tr>
            <th>제품 이름</th>
            <td>${product.modelName}</td>
        </tr>
        <tr>
            <th>제품 이미지</th>
            <td>${product.productImage}</td>
        </tr>
        <tr>
            <th>제품 가격</th>
            <td>${product.unitCost}</td>
        </tr>
        <tr>
            <th>제품 설명</th>
            <td>${product.description}</td>
        </tr>
    </tbody>
</table>
<ul>
    <li><a href="/admin/product.do?page=${page}">리스트</a></li>
    <li>
        <c:url var="update_link" value="/admin/product/update.do">
            <c:param name="id" value="${product.productId}"/>
        </c:url>
        <a href="${update_link}">수정</a>
    </li>
        <li>
            <form method="post" action="/admin/product/delete.do">
                <input type="hidden" name="id" value="${product.productId}">
                <button class="w-100 btn btn-lg btn-primary mt-3"  type="submit">삭제</button>
            </form>
        </li>
</ul>