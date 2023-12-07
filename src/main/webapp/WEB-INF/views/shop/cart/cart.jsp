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
            <th scope="col">추가한 일자</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="index" begin="0" end="${fn:length(list)-1}">
            <c:set var="item" value="${list[index]}"/>
            <c:set var="product" value="${productList[index]}"/>
            <tr>
                <td>${product.modelName}</td>
                <td>${item.quantity}</td>
                <td>${product.unitCost*item.quantity}</td>
                <td>${item.dateCreated}</td>
                <td>
                    <ul class="list-group list-group-horizontal">
                        <li class="list-group-item">
                            <form method="post" action="/cart.do">
                                <input type="number" class="form-control" name="quantity"  style="width: 70px;">
                                <input type="hidden" class="form-control" name="productId" value="${item.productId}">
                                <button type="submit">수량 수정</button>
                            </form>
                        </li>
                        <li class="list-group-item">
                            <form method="post" action="/cart/delete.do">
                                <input type="hidden" name="id" value="${item.productId}">
                                <button type="submit">삭제</button>
                            </form>
                        </li>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
            <%--todo 장바구니 주문버튼 만들기--%>
        <c:url var="pay_link" value="/pay.do" scope="request">
        </c:url>
        <a class="nav-link" href="${pay_link}">주문</a>
    </div>
</c:if>
