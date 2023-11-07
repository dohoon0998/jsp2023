<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="Login.css" >
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        <form id="loginForm" action="LoginProcess.jsp">
            <div class="form-group">
                <label for="username">사용자 이름</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">로그인</button>
            </div>
        </form>
        <div class="signup-link">
            계정이 없으신가요? <a href="signup.html">회원가입</a>
        </div>
        <div id="message" style="color: #ff0000;"></div>
    </div>

    <script>
        document.getElementById("loginForm").addEventListener("submit", function (e) {
            e.preventDefault();
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            // 실제 로그인 처리를 여기에 추가합니다.
            if (username === "사용자이름" && password === "비밀번호") {
                document.getElementById("message").innerHTML = "환영합니다, " + username + "님!";
            } else {
                document.getElementById("message").innerHTML = "로그인 실패. 사용자 이름 또는 비밀번호를 확인하세요.";
            }
        });
    </script>
</body>
</html>
