<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!-- jstl substring호출 -->
<%@ page import = "cash.vo.*" %>
<%
	Member member = (Member) request.getAttribute("member");
	// System.out.println(member);
%>
<!-- jsp컴파일 시 자바코드로 변환되는 c:... (제어문법코드) 커스텀태그 사용가능-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Calendar</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		table {
			width: 100%;
		}
		table,tr,th,td {
			border: 1px solid;
		}
		th,td {
			text-align: center;
		}
		table td > div:nth-child(2){
			position:relative; 
	    	overflow-y: auto;
			height: 110px;
			max-width: 500px;
		}
		.flex-container {
			display: flex;
			align-items: center;
			justify-content: center;
			text-align: center;
	    }
	    .flex-container a {
			margin-right: 10px;
			margin-left: 10px;
			font-size:xx-large;
			color:black;
			text-decoration-line: none;
	    }
	    .menu {
	   		display: flex;
			align-items: center;
			justify-content: center;
			text-align: center;
	    }
	    .mBtn {
	    	margin-right: 15px;
	    }
	    .hashTag {
	    	text-decoration-line: none; 
	    	color : black;
	    }
	    .scroll {
	    	position:relative; 
	    	overflow-y: auto;
	    	width: 150px;
	    	height: 80px;
	    }
	</style>
</head>
<body>
	<!-- 변수값 or 반환값 : EL 사용 $ 표현식 -->
	<!-- 속성값 대신 EL 사용 
		ex)
		request.getAttribute("targetYear") >> requestScope.targetYear 
		(requestScope는 생략가능)
		형변환연산이 필요없다(EL이 자동으로 처리)
	--> 
	<!-- 자바코드(제어문) : JSTL -->
	 <div class="flex-container">
			<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth-1}">◀</a>
			<h1>${targetYear}년 ${targetMonth+1}월</h1>
			<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth+1}">▶</a>
	</div>
	<div class="menu">
	 	<a type="button" class="btn btn-outline-secondary mBtn" href="${pageContext.request.contextPath}/memberOne">회원정보</a>
		<a type="button" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/logout">로그아웃</a>
	</div>
	<br>
	<!-- 해시태그 -->
	<div>
		<h3 style="text-align: center;">이달의 해시태그</h3>
		<div style="text-align: center;">
			<c:forEach var="m" items="${htList}">
				<a class="hashTag" href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">
					#${m.word}(${m.cnt})
				</a>
			</c:forEach>
		</div>
	</div>
	
	<!-- 캘린더 및 가계부 -->
	<table class="table table-hover">
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<c:forEach var="i" begin="0" end="${totalCell-1}" step="1">
				<c:set var="d" value="${i-beginBlank+1}"></c:set> <!-- i-beginBlanck+1 을 줄여서 d > 변수선언 -->		
						
				<c:if test="${i != 0 && i%7 == 0}">
					</tr><tr>
				</c:if>
				
				<c:if test="${d < 1 || d > lastDate}">
					<td></td>
				</c:if>
				
				<c:if test="${!(d < 1 || d > lastDate)}">
					<td>
						<div>
							<a style="color:black" href="${pageContext.request.contextPath}/calendarOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">${d}</a>
						</div>
						<div>
							<c:forEach var="c" items="${list}">
								<c:if test="${d == fn:substring(c.cashbookDate, 8, 10)}">
									<div>
										<c:if test="${c.category == '수입'}">
											<span style="color:blue">+${c.price}</span>
										</c:if>
										<c:if test="${c.category == '지출'}">
											<span style="color:red;">-${c.price}</span>
										</c:if>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</td>
				</c:if>
			</c:forEach>
	</table>
	<%-- 
	<table>
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<%
				for(int i=0; i<totalCell; i++){
					
					if(i%7 == 0){ // 7일 넘어가면 줄바꿈(한주 경과)
			%>
						</tr>
						
						<tr>		
			<%
					}		
						// >마지막날짜 || <1  >> 30/31을 넘거나 1이전이면 공백
						if((i-beginBlank +1) > lastDate || (i-beginBlank+1) < 1) {
			%>
							<td>&nbsp;</td>
			<%
						} else {
			%>
							<td>
								<a><%=i-beginBlank+1%></a>
							</td>
			<%			
						}
				}
			%>
						</tr>
	</table>	 --%>
</body>
</html>