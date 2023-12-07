<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/02
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>제품-등록</h1>
<c:set var="action" value="/admin/product/register.do"/>
<c:if test="${not empty product}">
    <c:set var="action" value="/admin/product/update.do"/>
</c:if>
<form method="post" action="${action}">
    <table>
        <tr>
            <td>제품 아이디</td>
            <td>
                <input type="text" name="id" value="${product.productId}" ${not empty product ? 'readonly' : ''}required><br/>
            </td>
        </tr>
        <tr>
            <td>카테고리 아이디</td>
            <td>
                <input type="text" name="categoryId" value="${not empty product ? product.categoryId : ''}" required><br/>
            </td>
        </tr>
        <tr>
            <td>모델 번호</td>
            <td>
                <input type="text" name="number" value="${not empty product ? product.modelNumber : ''}" required><br/>
            </td>
        </tr>
        <tr>
            <td>모델 이름</td>
            <td>
                <input type="text" name="name" value="${not empty product ? product.modelName : ''}" required><br/>
            </td>
        </tr>
        <tr>
            <td>제품 이미지</td>
            <td>
                <input type="text" name="image" value="${not empty product ? product.productImage : ''}" required><br/>
            </td>
        </tr>

        <tr>
            <td>제품 가격</td>
            <td>
                <input type="text" name="cost" value="${not empty product ? product.unitCost : ''}" required><br/>
            </td>
        </tr>
        <tr>
            <td>제품 설명</td>
            <td>
                <input type="text" name="description" value="${not empty product ? product.description : ''}" required><br/>
            </td>
        </tr>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty product}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>