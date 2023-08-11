<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "cash.vo.*" %>
<%@ page import = "cash.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>memberOne</title>
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
		#form {
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
		<div id="form" class="center-text">
			<h1>회원 상세정보</h1>
			<table class="table table-hover">
				<tr>
					<th>아이디</th>
					<td>
						${member.memberId}
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>*******</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${member.createdate}</td>
				</tr>
			</table>
			<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/modifyMember">회원정보 수정</a>
			<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/removeMember">회원 탈퇴</a>
			<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/cashbook">달력으로</a>
		</div>
	</div>
</body>
</html>