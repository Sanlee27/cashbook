package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.KeyStoreUtil;

import cash.model.CashbookDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/calendarOne")
public class CalendarOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		// System.out.println(loginMember);
		
		// 값 저장
		String memberId = loginMember.getMemberId();
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(memberId + " : memberId");
		System.out.println(targetYear + " : targetYear");
		System.out.println(targetMonth + " : targetMonth");
		System.out.println(targetDate + " : targetDate");
		
		// 모델 값 구하기
		CashbookDao dao = new CashbookDao();
		List<Cashbook> list = dao.selectCashbookOne(memberId, targetYear, targetMonth+1, targetDate);
		System.out.println(list + " : list");
		
		request.setAttribute("list", list);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		
		request.getRequestDispatcher("/WEB-INF/view/calendarOne.jsp").forward(request, response);
	}

}
