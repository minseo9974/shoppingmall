<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/06
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>배송지</h3>
<p><a href="/myPage/address/register.do">배송지(등록)</a></p>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">고객 아이디</th>
        <th scope="col">시/군/도</th>
        <th scope="col">구</th>
        <th scope="col">상세 주소</th>
        <th scope="col">우편 번호</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.userId}</td>
            <td>${item.state}</td>
            <td>${item.city}</td>
            <td>${item.addressLine}</td>
            <td>${item.postalCode}</td>
            <td>
                <c:url var="update_link" value="/myPage/address/update.do" scope="request">
                    <c:param name="id" value="${item.addressId}"/>
                </c:url>
                <a href="${update_link}">수정</a>
            </td>
            <td>
                <c:url var="delete_link" value="/myPage/address/delete.do" scope="request">
                    <c:param name="id" value="${item.addressId}"/>
                </c:url>
                <a href="${delete_link}">삭제</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>