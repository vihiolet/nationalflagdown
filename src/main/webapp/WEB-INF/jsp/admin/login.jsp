<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <style>
        .login-container { width: 300px; margin: 100px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        input { width: 100%; padding: 8px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>

<div class="login-container">
    <h2>관리자 로그인</h2>
    <div class="form-group">
        <input type="text" id="id" placeholder="아이디" required>
    </div>
    <div class="form-group">
        <input type="password" id="password" placeholder="비밀번호" required>
    </div>
    <button type="button" onclick="handleLogin()">로그인</button>
</div>

<script>
    async function handleLogin() {
    
        const id = document.getElementById('id').value;
        const password = document.getElementById('password').value;

        if (!id || !password) {
            alert("아이디와 비밀번호를 모두 입력해주세요.");
            return;
        }

        const loginData = {
            id: id,
            password: password
        };

        try {
            //fetch 요청 보내기
            const response = await fetch('/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            });

            if (response.ok) {
                alert("로그인에 성공했습니다.");
                window.location.href = "/adminNation"; 
            } else {
                // 실패 시 (401 에러 등)
                const errorMsg = await response.text();
                alert(errorMsg);
            }
        } catch (error) {
            console.error("로그인 중 오류 발생:", error);
            alert("서버와 통신 중 오류가 발생했습니다.");
        }
    }
</script>

</body>
</html>