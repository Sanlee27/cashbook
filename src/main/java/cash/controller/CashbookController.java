package cash.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Member;

/**
 * Servlet implementation class CashbookController
 */
@WebServlet("/cashbook")
public class CashbookController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		// 아이디값 저장
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘날짜
		
		// 최초 년/월/일은 이번달 1일
		firstDay.set(Calendar.DATE, 1); // 1일
		
		// 출력 대상 년/월이 넘어왔다면(null이 아니라면)
		if(request.getParameter("targetYear") != null && request.getParameter("targetMonth") != null) {
			// 지정 년 월
			firstDay.set(Calendar.YEAR, Integer.parseInt(request.getParameter("targetYear"))); // 대상 년을 세팅
			firstDay.set(Calendar.MONTH, Integer.parseInt(request.getParameter("targetMonth"))); // 대상 월을 세팅 (1/12월의 경우 API에서 이전해/다음해 자동연산)
		}
			
		// 바뀐 년/월 다시 저장
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		
		System.out.println(targetYear + " : targetYear");
		System.out.println(targetMonth + " : targetMonth");
		
		// 달력출력시 시작공백
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) - 1; // 1일날짜의 요일(일1, 월2, ..토6) -1 > 검색하면 나옴
		System.out.println(beginBlank + " : beginBlank");
		
		// 출력 대상 월의 마지막 날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		System.out.println(lastDate + " : lastDate");
		
		// 마지막 날짜 출력 후 공백 개수 > 전체 출력 셀의 개수가 7로 나누어 떨어져야됨
		int endBlank = 0;
		if(((beginBlank + lastDate) % 7) !=0) {
			endBlank = 7 - (beginBlank + lastDate) % 7;
		}
		
		int totalCell = beginBlank + lastDate + endBlank;
		System.out.println(totalCell + " : totalCell");
		System.out.println(endBlank + " : endBlank");
		
		// 모델을 호출(DAO 타겟 월의 수입/지출 데이터)
		List<Cashbook> list = new CashbookDao().selectCashbookListByMonth(memberId, targetYear, targetMonth+1);
		
		List<Map<String,Object>> htList = new HashtagDao().selectWordCountByMonth(memberId, targetYear, targetMonth+1);
		
		// 뷰에 값넘기기(request 속성)
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("endBlank", endBlank);
		
		request.setAttribute("list", list);
		request.setAttribute("htList", htList);
		// 달력 출력하는 뷰
		
		request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);
	}

}
