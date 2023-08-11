<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "cash.vo.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modifyMember</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		$(document).ready(function() {
	        // url에서 msg 추출
	        function pwCheck() {
	            const urlParams = new URLSearchParams(window.location.search);
	            return urlParams.get('msg');
	        }
	
	        // msg있으면 alert 실행
	        const msg = pwCheck();
	        if (msg) {
	            alert(msg);
	        }
	        
	        let pw = $('input[name="memberPw"]');
	        let chPw = $('input[name="memberPw2"]');
	        
	        // 비밀번호 재입력 일치여부 확인
	        chPw.blur(function(){
	        	if(pw.val() !== chPw.val()){
	        		alert("비밀번호가 일치하지않습니다.");
	        		chPw.val("");
	        	}
	        });
		});
    </script>
    <style>
		#form-container {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		    margin: 0;
		}
		form {
		    border: 10px ridge #ccc;
		    padding: 20px;
		    max-width: 400px;
		}
		.center-text {
		    text-align: center;
		}
	</style>
</head>
<body>
	<div id="form-container">
		<form action="${pageContext.request.contextPath}/modifyMember" method="post" class="center-text">
			<h1>회원정보 수정</h1>
			<table class="table table-hover">
				<tr>
					<th>아이디</th>
					<td>${member.memberId}</td>
				</tr>
				<tr>
					<th>비밀번호 변경</th>
					<td>
						<input type="password" name="memberPw" placeholder="비밀번호를 입력하세요" required="required">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="memberPw2" placeholder="비밀번호를 한번 더 입력하세요" required="required">
					</td>
				</tr>
			</table>
			<button id="submit" type="submit" class="btn btn-outline-secondary">수정하기</button>
			<a type="button" class="btn btn-outline-secondary mBtn" href="${pageContext.request.contextPath}/memberOne">뒤로가기</a>
		</form>
	</div>
</body>
</html>