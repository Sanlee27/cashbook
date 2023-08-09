<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login.jsp</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%-- ${pageContext.request.contextPath} > request.getContextPath를 대신하는 EL --%>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<h1>가계부 로그인</h1>
		<br>
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" placeholder="아이디를 입력하세요" value="user1" required="required">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="memberPw" placeholder="비밀번호를 입력하세요" value="1234" required="required">
				</td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-secondary">로그인</button>
		<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/addMember">회원가입</a>
	</form>
</body>
</html>