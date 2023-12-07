<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/08
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<div class="d-flex justify-content-center">
    <%--todo 장바구니 주문버튼 만들기--%>
    <c:url var="delete_link" value="/exit/delete.do" scope="request">
    </c:url>
    <a class="nav-link" href="${delete_link}">탈퇴</a>
</div>