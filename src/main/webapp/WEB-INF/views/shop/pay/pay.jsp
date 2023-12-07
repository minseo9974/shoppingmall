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

<!-- 주문 정보를 보여주는 섹션 -->
<section>
    <h3>주문 정보</h3>
    <ul>
        <li><strong>내 보유 포인트:</strong> ${point}</li>
        <li><strong>총 결제 금액:</strong> ${totalCost}</li>
    </ul>
</section>

<!-- 배송지 목록을 선택하는 섹션 -->
<section>
    <h3>배송지 선택</h3>
    <select id="deliveryAddress" name="deliveryAddress">
        <c:forEach var="address" items="${addressList}">
            <option value="${address.addressId}">${address.toString()}</option>
        </c:forEach>
    </select>
</section>

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

    <!-- 결제 버튼 -->
<div class="d-flex justify-content-center">
    <form action="/pqy.do" method="post">
        <input type="hidden" name="totalCost" value="${totalCost}">
        <input type="hidden" name="selectedAddressId" id="selectedAddressId">
        <button type="submit">결제</button>
    </form>
</div>


<!-- JavaScript를 사용하여 배송지 선택 시 selectedAddressId 업데이트 -->
<script>
    const deliveryAddressSelect = document.getElementById("deliveryAddress");
    const selectedAddressIdInput = document.getElementById("selectedAddressId");

    deliveryAddressSelect.addEventListener("change", function () {
        selectedAddressIdInput.value = this.value;
    });
</script>