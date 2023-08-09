<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "cash.vo.*" %>
<%
	Member member = (Member)request.getAttribute("member");
	// System.out.println(member);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modifyMember</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action="${pageContext.request.contextPath}/modifyMember" method="post">
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<td><%=member.getMemberId()%></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="memberPw" required="required">
				</td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-secondary">수정하기</button>
		<a type="button" class="btn btn-outline-secondary mBtn" href="${pageContext.request.contextPath}/memberOne">뒤로가기</a>
	</form>
</body>
</html>