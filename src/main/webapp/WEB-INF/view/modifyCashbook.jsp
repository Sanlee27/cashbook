<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "cash.vo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modify Cashbook</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		#form-container {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		}
		
		.center-text,th {
		    text-align: center;
		}
	</style>
</head>
<body>
	<div id="form-container">
	<form action="${pageContext.request.contextPath}/modifyCashbook" method="post">
		<br>
		<h1 class="center-text">가계 내역 수정</h1>
		<br>
		<table class="table table-hover">
			<tr>
				<th>날짜</th>
				<td>
					<input type="hidden" name="targetYear" value="${targetYear}">
					<input type="hidden" name="targetMonth" value="${targetMonth}">
					<input type="hidden" name="targetDate" value="${targetDate}">
					<input type="hidden" name="cashbookNo" value="${cashbook.cashbookNo}">
					<input type="hidden" name="memberId" value="${cashbook.memberId}">
					<input type="text" name="cashbookDate" value="${targetYear}-${targetMonth+1}-${targetDate}" required="required">
				</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>
					<select name="category">
					<c:if test="${cashbook.category == '수입'}">
						<option value="수입" selected="selected">수입</option>
						<option value="지출">지출</option>
					</c:if>
					<c:if test="${cashbook.category == '지출'}">
						<option value="수입">수입</option>
						<option value="지출" selected="selected">지출</option>
					</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<th>금액</th>
				<td>
					<input type="number" min="0" name="price" value="${cashbook.price}" required="required">
				</td>
			</tr>
			<tr>
				<th>메모</th>
				<td>
					<textarea name="memo" rows="2" cols="80">${cashbook.memo}</textarea>
				</td>
			</tr>
		</table>
		<div class="center-text">
			<button type="submit" class="btn btn-outline-secondary">수정하기</button>
			<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/calendarOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">뒤로가기</a>
		</div>
	</form>
	</div>
</body>
</html>