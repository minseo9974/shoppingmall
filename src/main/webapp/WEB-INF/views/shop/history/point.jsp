<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/08
  Time: 12:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>제품 리스트</h3>

<table class="table table-striped">
  <thead>
  <tr>
    <th scope="col">거래 번호</th>
    <th scope="col">고객</th>
    <th scope="col">거래 타입</th>
    <th scope="col">거래량(원)</th>
    <th scope="col">거래 일시</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="item" items="${list}">
    <tr>
      <td>${item.historyId}</td>
      <td>${item.userId}</td>
      <td>${item.type}</td>
      <td>${item.amount}</td>
      <td>${item.date}</td>
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
