<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/03
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>유저 리스트</h3>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">아이디</th>
        <th scope="col">이름</th>
        <th scope="col">비밀번호</th>
        <th scope="col">생일</th>
        <th scope="col">등급</th>
        <th scope="col">포인트</th>
        <th scope="col">생성일</th>
        <th scope="col">마지막 접속일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${userList}">
        <tr>
            <td>${item.userId}</td>
            <td>${item.userName}</td>
            <td>${item.userPassword}</td>
            <td>${item.userBirth}</td>
            <td>${item.userAuth}</td>
            <td>${item.userPoint}</td>
            <td>${item.createdAt}</td>
            <td>${item.latestLoginAt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
        <c:if test="${userPh.totalCnt == null || userPh.totalCnt == 0}">
            <div> 제품이 없습니다.</div>
        </c:if>
        <c:if test="${userPh.totalCnt != null && userPh.totalCnt != 0}">
            <ul class="pagination">
                <c:if test="${userPh.showPrev}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${userPh.beginPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="${userPh.beginPage}" end="${userPh.endPage}">
                    <c:choose>
                        <c:when test="${i eq userPh.getSc().getPage()}">

                            <li class="page-item"><a class="page-link"><b>${i}</b></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${userPh.showNext}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${userPh.endPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </nav>
</div>
