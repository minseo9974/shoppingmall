<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/01
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>제품 리스트</h3>
<p><a href="/admin/product/register.do">제품(등록)</a></p>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">제품 아이디</th>
        <th scope="col">카텍고리 아이디</th>
        <th scope="col">모델 번호</th>
        <th scope="col">모델 이름</th>
        <th scope="col">제품 사진</th>
        <th scope="col">제품 가격</th>
        <th scope="col">제품 설명</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.productId}</td>
            <td>${item.categoryId}</td>
            <td>${item.modelNumber}</td>
            <td>${item.modelName}</td>
            <td>${item.productImage}</td>
            <td>${item.unitCost}</td>
            <td>${item.description}</td>
            <td>
                <c:url var="view_link" value="/admin/product/view.do" scope="request">
                    <c:param name="id" value="${item.productId}"/>
                    <c:param name="view_page" value="${ph.getSc().getPage()}"/>
                </c:url>
                <a href="${view_link}">조회</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
        <c:if test="${ph.totalCnt == null || ph.totalCnt == 0}">
            <div> 제품이 없습니다.</div>
        </c:if>
        <c:if test="${ph.totalCnt != null && ph.totalCnt != 0}">
            <ul class="pagination">
                <c:if test="${ph.showPrev}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${ph.beginPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <c:choose>
                        <c:when test="${i eq ph.getSc().getPage()}">

                            <li class="page-item"><a class="page-link"><b>${i}</b></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${ph.showNext}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${ph.endPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </nav>
</div>
