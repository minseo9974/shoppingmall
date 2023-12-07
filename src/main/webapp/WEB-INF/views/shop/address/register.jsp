<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/06
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>배송지-등록</h1>
<c:set var="action" value="/myPage/address/register.do"/>
<c:if test="${not empty address}">
    <c:set var="action" value="/myPage/address/update.do"/>
</c:if>
<form method="post" action="${action}">
    <table>
        <input type="hidden" name="id" value="${address.addressId}"><br/>
        <tr>
            <td>state</td>
            <td>
                <input type="text" name="state" value="${not empty address ? address.state : ''}" required><br/>
            </td>
        </tr>
        <tr>
            <td>city</td>
            <td>
                <input type="text" name="city" value="${not empty address ? address.city : ''}" required><br/>
            </td>
        </tr>

        <tr>
            <td>address-line</td>
            <td>
                <input type="text" name="text" value="${not empty address ? address.addressLine : ''}" required><br/>
            </td>
        </tr>
        <tr>
            <td>우편번호</td>
            <td>
                <input type="text" name="code" value="${not empty address ? address.postalCode : ''}" required><br/>
            </td>
        </tr>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty address}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>