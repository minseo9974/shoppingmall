<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>카테고리-등록</h1>
<c:set var="action" value="/admin/category/register.do"/>
<c:if test="${not empty category}">
    <c:set var="action" value="/admin/category/update.do"/>
</c:if>
<form method="post" action="${action}">
    <table>
        <tr>
            <td>ID</td>
            <td>
                <input type="text" name="id" value="${category.categoryId}" ${not empty category ? 'readonly' : ''}required><br/>
            </td>
        </tr>
        <tr>
            <td>이름</td>
            <td>
                <input type="text" name="name" value="${not empty category ? category.categoryName : ''}" required><br/>
            </td>
        </tr>
    </table>
    <p>
        <button class="w-100 btn btn-lg btn-primary mt-3"  type="submit">
            <c:choose>
                <c:when test="${empty category}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>