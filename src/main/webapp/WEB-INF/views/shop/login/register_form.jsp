<%--
  Created by IntelliJ IDEA.
  User: minseo
  Date: 2023/11/30
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" trimDirectiveWhitespaces="true" %>


<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/signup.do">
            <h1 class="h3 mb-3 fw-normal">회원가입</h1>

            <div class="form-floating">
                <input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디" required>
                <label for="user_id">회원아이디</label>
            </div>

            <div class="form-floating">
                <input type="text" class="form-control" name="user_name" id="user_name" placeholder="이름" required>
                <label for="user_name">회원이름</label>
            </div>

            <div class="form-floating">
                <input type="password" class="form-control" name="user_password" id="user_password" placeholder="비밀번호"
                       required>
                <label for="user_password">회원비밀번호</label>
            </div>

            <div class="form-floating">
                <input type="text" class="form-control" name="user_birth" id="user_birth" placeholder="생일" required>
                <label for="user_birth">회원생일</label>
            </div>

            <div class="form-floating">
                <h1 class="h3 mb-3 fw-normal">회원 등급</h1>

                <div>
                    <input type="radio" name="user_auth" id="user" value="ROLE_USER" checked class="form-check-input">
                    <label for="user" class="form-check-label">일반회원</label>
                </div>

                <div>
                    <input type="radio" name="user_auth" id="admin" value="ROLE_ADMIN" class="form-check-input">
                    <label for="admin" class="form-check-label">관리자</label>
                </div>
            </div>
            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">SEND</button>
            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>