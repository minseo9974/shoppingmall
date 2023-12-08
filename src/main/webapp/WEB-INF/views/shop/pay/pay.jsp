<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/05
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<h2>결제 품목</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">상품명</th>
        <th scope="col">상품코드</th>
        <th scope="col">수량(개)</th>
        <th scope="col">가격(원)</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="index" begin="0" end="${fn:length(list)-1}">
        <c:set var="item" value="${list[index]}"/>
        <c:set var="product" value="${productList[index]}"/>
        <tr>
            <td>${product.modelName}</td>
            <td>${product.modelNumber}</td>
            <td>${item.quantity}</td>
            <td>${product.unitCost*item.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<!-- 주문 정보를 보여주는 섹션 -->
<section>
    <h3>주문 정보</h3>
    <ul>
        <li><strong>내 보유 포인트:</strong> ${point}</li>
        <li><strong>총 결제 금액:</strong> ${totalCost}</li>
        <c:if test="${point >= totalCost}">
            <li><strong>결제후 예상 금액:</strong> ${point - totalCost}</li>
        </c:if>
    </ul>
</section>
<form action="/pay.do" method="post">
    <!-- 배송지 목록을 선택하는 섹션 -->
    <section>
        <h3>배송지 선택</h3>
        <select id="deliveryAddress" name="deliveryAddress" required>
            <option value="" disabled selected hidden>배송지 선택</option>
            <c:forEach var="address" items="${addressList}">
                <option value="${address.toString()}">${address.toString()}</option>
            </c:forEach>
        </select>
    </section>

    <!-- 결제 버튼 -->
    <div class="d-flex justify-content-center">

        <input type="hidden" name="totalCost" value="${totalCost}">
        <input type="hidden" name="selectedAddressId" id="selectedAddressId">
        <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">결제</button>

    </div>
</form>