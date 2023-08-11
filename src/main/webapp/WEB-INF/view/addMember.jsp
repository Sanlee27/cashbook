<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>add Member</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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
	<form action="${pageContext.request.contextPath}/addMember" method="post">
		<h1 class="center-text" style="font-weight: bold;">회원가입</h1>
		<br>
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" placeholder="아이디를 입력하세요" required="required">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="memberPw" placeholder="비밀번호를 입력하세요" required="required">
				</td>
			</tr>
		</table>
		<div class="center-text">
			<button type="submit" class="btn btn-outline-secondary">회원가입</button>
			<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/login">뒤로가기</a>
		</div>
	</form>
	</div>
</body>
</html>