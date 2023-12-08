<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/03
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-5">
  <h1 class="mb-4">회원 수정</h1>

  <form method="post" action="/myPage/update.do">
    <table class="table">
      <tr>
        <td class="font-weight-bold">아이디</td>
        <td>${user.userId}<input type="hidden" name="id" value="${user.userId}"></td>
      </tr>
      <tr>
        <td class="font-weight-bold">이름</td>
        <td><input type="text" name="name" value="${not empty user ? user.userName : ''}" class="form-control" required></td>
      </tr>
      <tr>
        <td class="font-weight-bold">비밀번호</td>
        <td><input type="text" name="password" value="${not empty user ? user.userPassword : ''}" class="form-control" required></td>
      </tr>
      <tr>
        <td class="font-weight-bold">생일</td>
        <td><input type="text" name="birth" value="${not empty user ? user.userBirth : ''}" class="form-control" required></td>
      </tr>
      <tr>
        <td class="font-weight-bold">등급</td>
        <td>${user.userAuth}<input type="hidden" name="auth" value="${user.userAuth}"></td>
      </tr>
      <tr>
        <td class="font-weight-bold">포인트</td>
        <td>${user.userPoint}<input type="hidden" name="point" value="${user.userPoint}"></td>
      </tr>
      <tr>
        <td class="font-weight-bold">생성일자</td>
        <td>${user.createdAt}<input type="hidden" name="createdAt" value="${user.createdAt}"></td>
      </tr>
      <tr>
        <td class="font-weight-bold">최근 접속일</td>
        <td>${user.latestLoginAt}<input type="hidden" name="latestLoginAt" value="${user.latestLoginAt}"></td>
      </tr>
    </table>
    <button class="btn btn-primary" type="submit">수정</button>
  </form>
</div>
