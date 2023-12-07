<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/12/03
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>회원-수정</h1>

<form method="post" action="/myPage/update.do">
  <table>
    <tr>
      <td>아이디</td>
      <td>
        ${user.userId}<br/>
          <input type="hidden" name="id" value="${user.userId}" />
      </td>
    </tr>
    <tr>
      <td>이름</td>
      <td>
        <input type="text" name="name" value="${not empty user ? user.userName : ''}" required><br/>
      </td>
    </tr>
    <tr>
      <td>비밀번호</td>
      <td>
        <input type="text" name="password" value="${not empty user ? user.userPassword : ''}" required><br/>
      </td>
    </tr>
    <tr>
      <td>생일</td>
      <td>
        <input type="text" name="birth" value="${not empty user ? user.userBirth : ''}" required><br/>
      </td>
    </tr>
    <tr>
      <td>등급</td>
      <td>
        <input type="text" name="auth" value="${not empty user ? user.userAuth : ''}" required><br/>
      </td>
    </tr>

    <tr>
      <td>포인트</td>
      <td>
        ${user.userPoint}
          <input type="hidden" name="point" value="${user.userPoint}" />
      </td>
    </tr>
    <tr>
      <td>생성일자</td>
      <td>
        ${user.createdAt}
          <input type="hidden" name="createdAt" value="${user.createdAt}" />
      </td>
    </tr>
    <tr>
      <td>최근 접속일</td>
      <td>
        ${user.latestLoginAt}
          <input type="hidden" name="latestLoginAt" value="${user.latestLoginAt}" />
      </td>
    </tr>
  </table>
  <p>
    <button class="w-100 btn btn-lg btn-primary mt-3"  type="submit">수정</button>
  </p>
</form>