<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/08
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="auth" value="${sessionScope.auth}"/>

<script>
    function goBack() {
        window.history.back();
    }
</script>
<div class="container mt-5">
    <h3>상품 상세 페이지</h3>
    <table class="table">
        <tbody>
        <tr>
            <th scope="row">상품 이미지</th>
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                 xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                 preserveAspectRatio="xMidYMid slice" focusable="false">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#55595c"></rect>
                <image href="${product.productImage}" width="100%" height="100%"/>
            </svg>
        </tr>
        <tr>
            <th scope="row">제품명</th>
            <td>${product.modelName}</td>
        </tr>
        <tr>
            <th scope="row">설명</th>
            <td>${product.description}</td>
        </tr>
        <tr>
            <th scope="row">가격</th>
            <td>${product.unitCost}원</td>
        </tr>
        </tbody>
    </table>
    <ul class="list-inline">
        <li class="list-inline-item">
            <button class="btn btn-primary" onclick="goBack()">이전 페이지</button>
        </li>
        <c:if test="${auth ne 'ROLE_ADMIN'}">
            <li class="list-inline-item">
                <c:url var="cart_link" value="/cart.do">
                    <c:param name="productId" value="${product.productId}"/>
                </c:url>
                <a class="btn btn-success" href="${cart_link}">장바구니 추가</a>
            </li>
        </c:if>
    </ul>
</div>