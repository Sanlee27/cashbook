<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add Member</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath}/addMember" method="post">
		<table border="1">
			<tr>
				<th>memberId</th>
				<td>
					<input type="text" name="memberId" placeholder="아이디를 입력하세요">
				</td>
			</tr>
			<tr>
				<th>memberPw</th>
				<td>
					<input type="password" name="memberPw" placeholder="비밀번호를 입력하세요">
				</td>
			</tr>
		</table>
		<br>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>