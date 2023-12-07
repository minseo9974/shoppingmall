<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/11/30
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" trimDirectiveWhitespaces="true" %>


<div class="black-box">
    <div class="white-box">
        <form method="post" action="/signup.do">
                <h3>회원 가입</h3>
            <div>
                <label for="user_id">회원아이디</label>
                <input type="text" name="user_id" id="user_id" placeholder="아이디" required>
            </div>
            <div>
                <label for="user_name">회원이름</label>
                <input type="text" name="user_name" id="user_name" placeholder="이름" required>
            </div>
            <div>
                <label for="user_password">회원비밀번호</label>
                <input type="password" name="user_password" id="user_password" placeholder="비밀번호" required>
            </div>
            <div>
                <label for="user_birth">회원생일</label>
                <input type="text" name="user_birth" id="user_birth" placeholder="생일" required>
            </div>
            <div>
                <h4>회원등급</h4>
                <label for="user">일반회원</label>
                <input type="radio" name="user_auth" id="user" value="ROLE_USER" checked>
                <label for="admin">관리자</label>
                <input type="radio" name="user_auth" id="admin" value="ROLE_ADMIN" >
            </div>

            <div>
                <button type="submit">SEND</button>
            </div>
        </form>
    </div>
</div>