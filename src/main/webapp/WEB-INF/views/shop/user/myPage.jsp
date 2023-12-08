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
    if (msg == "ADD_ERR") alert("장바구니에 존재하는 상품입니다!");
</script>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <c:if test="${not empty user}">
                <div class="text-center mb-4">
                    <h4>${user.userName}님, 환영합니다!</h4>
                    <h5>보유 잔액 : ${user.userPoint}원</h5>
                </div>
            </c:if>
            <div class="list-group">
                <a href="<c:url value="/myPage/update.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-person"></i> 회원 정보 수정
                </a>
                <a href="<c:url value="/order/view.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-card-list"></i> 주문 명세 조회
                </a>
                <a href="<c:url value="/history.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-cash"></i> 포인트 사용 내역 조회
                </a>
                <a href="<c:url value="/myPage/address.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-house-door"></i> 주소 관리
                </a>
                <a href="<c:url value="/exit.do"/>" class="list-group-item list-group-item-action">
                    <i class="bi bi-door-closed"></i> 회원 탈퇴
                </a>
            </div>
        </div>
    </div>
</div>
