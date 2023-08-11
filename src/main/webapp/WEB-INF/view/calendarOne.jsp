<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>calendarOne</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		.head {
			display: flex;
			align-items: center;
			justify-content: center;
			text-align: center;
		}
		.mBtn {
	    	margin-right: 15px;
	    }
	    
	    th,td {
			text-align: center;
		}
	</style>
</head>
<body>
	<h1 class="head">가계 내역</h1>
	<h2 class="head">${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h2>
	<br>
	<form action="${pageContext.request.contextPath}/removeCashbook" method="post">
		<table class="table table-hover">
			<tr style="background-color: #B7B7B7;">
				<th>구분</th>
				<th>금액</th>
				<th>메모</th>
				<th>작성일</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="one" items="${list}">
				<tr>
					<td>
						<input type="hidden" name="cashbookNo" value="${one.cashbookNo}">
						<input type="hidden" name="targetYear" value="${targetYear}">
						<input type="hidden" name="targetMonth" value="${targetMonth}">
						<input type="hidden" name="targetDate" value="${targetDate}">
						${one.category}
					</td>
					<c:if test="${one.category == '수입'}">
						<td style="color:blue">+<fmt:formatNumber value="${one.price}" pattern="#,###" />원</td>
					</c:if>
					<c:if test="${one.category == '지출'}">
						<td style="color:red;">-<fmt:formatNumber value="${one.price}" pattern="#,###" />원</td>
					</c:if>
					<td>${one.memo}</td>
					<td>${fn:substring(one.createdate,0,11)}</td>
					<td>
						<a href="${pageContext.request.contextPath}/modifyCashbook?cashbookNo=${one.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-secondary">수정</a>
					</td>
					<td>
						<button type="submit" class="btn btn-outline-secondary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<div class="head">
		<a type="button" class="btn btn-outline-secondary mBtn" href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">작성하기</a>
		<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}">달력보기</a>
	</div>
</body>
</html>