<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/04
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3>장바구니</h3>
<c:if test="${empty list}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">상품명</th>
            <th scope="col">수량</th>
            <th scope="col">추가한 일자</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <table class="table table-striped">
        <b>장바구니가 비었습니다.</b>
    </table>
</c:if>
<c:if test="${not empty list}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">상품명</th>
            <th scope="col">수량</th>
            <th scope="col">가격</th>
            <th scope="col">수량 수정</th>
            <th scope="col">삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.productName}</td>
                <td>${item.quantity}</td>
                <td>${item.unitCost * item.quantity}</td>
                <td>
                    <form method="post" action="/cart.do" class="d-flex">
                        <input type="hidden" name="productId" value="${item.productId}">
                        <input type="number" class="form-control me-2" name="quantity" style="width: 70px;" value="${item.quantity}">
                        <button class="btn btn-outline-primary" type="submit">수량 수정</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/cart/delete.do" class="d-flex">
                        <input type="hidden" name="id" value="${item.productId}">
                        <button class="btn btn-outline-danger ms-2" type="submit">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${not empty list}">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-6">
                    <c:url var="pay_link" value="/pay.do" scope="request"></c:url>
                    <a class="btn btn-primary btn-lg w-100" href="${pay_link}">주문</a>
                </div>
            </div>
        </div>

    </c:if>
</c:if>
