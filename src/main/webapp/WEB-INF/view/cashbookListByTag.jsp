<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>List by Tag</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		th,td {
			text-align: center;
		}
		.head {
			display: flex;
			align-items: center;
			justify-content: center;
			text-align: center;
		}	
		.mBtn {
	    	margin-right: 15px;
	    }
	</style>
</head>
<body>
	<h1 class="head">#${word}</h1>
	<br>
	<div class="head">
		<a type="button" class="btn btn-outline-secondary mBtn" href="${pageContext.request.contextPath}/cashbook">달력보기</a>
	 	<a type="button" class="btn btn-outline-secondary mBtn" href="${pageContext.request.contextPath}/memberOne">회원정보</a>
		<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/logout">로그아웃</a>
	</div>
	<br>
	<table class="table table-hover">
		<tr style="background-color: #B7B7B7;">
			<th>일자</th>
			<th>구분</th>
			<th>금액</th>
			<th>내용</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="t" items="${list}">
			<tr>
				<td>${t.cashbookDate}</td>
				<td>${t.category}</td>
				<c:if test="${t.category == '수입'}">
					<td style="color:blue">+${t.price}원</td>
				</c:if>
				<c:if test="${t.category == '지출'}">
					<td style="color:red;">-${t.price}원</td>
				</c:if>
				<td>${t.memo}</td>
				<td>${t.createdate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>