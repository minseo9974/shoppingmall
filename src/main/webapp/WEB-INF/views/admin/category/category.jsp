<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>카테고리 리스트</h3>
<p><a href="/admin/category/register.do">카테고리(등록)</a></p>
<table>
    <thead>
    <tr>
        <th>카테고리 아이디</th>
        <th>카테고리 이름</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${categoryList}">
        <tr>
            <td>${item.categoryId}</td>
            <td>${item.categoryName}</td>
            <td>
                <c:url var="view_link" value="/admin/category/view.do" scope="request">
                    <c:param name="id" value="${item.categoryId}"/>
                </c:url>
                <a href="${view_link}">조회</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
