<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 1:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    let msg = "${msg}";
    if (msg == "PAY_ERR") alert("결제 잔액이 부족합니다!");
    if(msg=="ADD_ERR")  alert("장바구니에 존재하는 상품입니다!");
</script>
<div class="d-flex justify-content-center">
    <div class="list-group">

        <ul class="list-group">
            <li class="list-group-item">
                <a href="<c:url value="/myPage/update.do"/> " class="list-group-item list-group-item-action">회원 정보
                    수정</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/order/view.do"/>  " class="list-group-item list-group-item-action">주문 명세 조회</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/history.do"/>" class="list-group-item list-group-item-action">포인트 사용내역 조회</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/myPage/address.do"/>" class="list-group-item list-group-item-action">주소 관리</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/exit.do"/>" class="list-group-item list-group-item-action">회원 탈퇴</a>
            </li>

        </ul>
    </div>
</div>