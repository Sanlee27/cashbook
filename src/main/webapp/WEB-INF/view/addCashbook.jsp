<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>add Cashbook</title>
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
	<form action="${pageContext.request.contextPath}/addCashbook" method="post">
		<br>
		<h1 class="center-text">가계부 내역 작성</h1>
		<br>
		<table class="table table-hover">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" value="${loginMemberId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>
					<input type="hidden" name="targetYear" value="${targetYear}">
					<input type="hidden" name="targetMonth" value="${targetMonth}">
					<input type="hidden" name="targetDate" value="${targetDate}">
					<input type="text" name="cashbookDate" value="${targetYear}-${targetMonth+1}-${targetDate}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>구분</th>
				<td>
					<select name="category">
						<option value="수입" selected="selected">수입</option>
						<option value="지출">지출</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>금액</th>
				<td>
					<input type="number" min="0" name="price" placeholder="금액" required="required">
				</td>
			</tr>
			<tr>
				<th>메모</th>
				<td>
					<textarea name="memo" rows="2" cols="80"></textarea>
				</td>
			</tr>
		</table>
		<div class="center-text">
			<button type="submit" class="btn btn-outline-secondary">등록하기</button>
			<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/cashbook">달력보기</a>
			<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/calendarOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">뒤로가기</a>
		</div>
	</form>
	</div>
</body>
</html>