package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.model.MemberDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@WebServlet("/modifyCashbook")
public class ModifyCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		System.out.println(loginMember);
		
		// 값 저장
		String memberId = loginMember.getMemberId();
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(memberId + " : memberId");
		System.out.println(targetYear + " : targetYear");
		System.out.println(targetMonth + " : targetMonth");	
		System.out.println(targetDate + " : targetDate");
		
		// 모델 값 구하기
		CashbookDao dao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		cashbook = dao.selectCashbook(cashbookNo);
		
		request.setAttribute("cashbook", cashbook);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		
		request.getRequestDispatcher("/WEB-INF/view/modifyCashbook.jsp?cashbookNo="+cashbookNo+"&targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 값 저장
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String memberId = request.getParameter("memberId");
		String cashbookDate = request.getParameter("cashbookDate");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		System.out.println(cashbookNo);
		System.out.println(memberId); 
		System.out.println(cashbookDate);
		System.out.println(category);
		System.out.println(price);
		System.out.println(memo);
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(targetYear + " : targetYear");
		System.out.println(targetMonth + " : targetMonth");	
		System.out.println(targetDate + " : targetDate");
		
		CashbookDao dao = new CashbookDao();
		Cashbook c = new Cashbook();
		c.setCashbookNo(cashbookNo);
		c.setMemberId(memberId);
		c.setCashbookDate(cashbookDate);
		c.setCategory(category);
		c.setPrice(price);
		c.setMemo(memo);
		
		System.out.println(c + "modify cashbook");
		
		int row = dao.updateCashbook(c);
		
		System.out.println(row + " << row");
		if(row == 0) {
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/modifyCashbook?cashbookNo="+cashbookNo+"&targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
			return;
		}
		
		HashtagDao hDao = new HashtagDao();
		
		int dRow = hDao.deleteHashtag(cashbookNo);
		System.out.println(dRow + " deleteHashtag");
		
		for(String cMemo : memo.split(" ")) {
			if(cMemo.startsWith("#")) {
				String htInMemo = cMemo.replace("#", "");
				if(htInMemo.length() > 0) {
					Hashtag hashtag = new Hashtag();
					hashtag.setCashbookNo(cashbookNo);
					hashtag.setWord(htInMemo);
					hDao.insertHashtag(hashtag);
				}
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/calendarOne?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}
		

}
