<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/06
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-4">
    <h3 class="text-center mb-4">주문 명세 리스트</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">주문 번호</th>
            <th scope="col">상품 번호</th>
            <th scope="col">수량</th>
            <th scope="col">가격</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.orderId}</td>
                <td>${item.productId}</td>
                <td>${item.quantity}</td>
                <td>${item.unitCost}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
        <a href="/order/view.do?page=${page}" class="btn btn-primary">목록</a>
    </div>
</div>