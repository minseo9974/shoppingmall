<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 1:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <a href="<c:url value="#"/>" class="list-group-item list-group-item-action">포인트 사용내역 조회</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="/myPage/address.do"/>" class="list-group-item list-group-item-action">주소 관리</a>
            </li>
            <li class="list-group-item">
                <a href="<c:url value="#"/>" class="list-group-item list-group-item-action">회원 탈퇴</a>
            </li>

        </ul>
    </div>
</div>