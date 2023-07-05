package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {
	// 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		 System.out.println(request.getParameter("targetYear") + " : rtargetYear");
		 System.out.println(request.getParameter("targetMonth") + " : rtargetMonth");
		 System.out.println(request.getParameter("targetDate") + " : rtargetDate");
		 
		 // request 매개값 
		 int targetYear = Integer.parseInt(request.getParameter("targetYear")); 
		 int targetMonth = Integer.parseInt(request.getParameter("targetMonth")); 
		 int targetDate= Integer.parseInt(request.getParameter("targetDate"));
		 String loginMemberId = ((Member) session.getAttribute("loginMember")).getMemberId();

		 System.out.println(targetYear + " : targetYear");
		 System.out.println(targetMonth + " : targetMonth");
		 System.out.println(targetDate + " : targetDate");
		 System.out.println(loginMemberId + " : loginMemberId");
		 
		 
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("loginMemberId", loginMemberId);
		
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
		// 나머지 데이터는 입력폼에서 사용자가 입력
	}

	// 입력액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request매개값
		request.setCharacterEncoding("utf-8");
		
		CashbookDao dao = new CashbookDao();
		
		String memberId = request.getParameter("memberId");
		String cashbookDate = request.getParameter("cashbookDate");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setCategory(category);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		int cashbookNo = dao.insertCashbook(cashbook); // 키값 반환 == fileupload에서 사용
		System.out.println(cashbookNo + " : cashbookNo");
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear")); 
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth")); 
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		if(cashbookNo == 0) {
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/addCashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&=targetDate="+targetDate);
			return;
		}
		// 입력 성공시 1) 해시태그가 있다면 2) 해시태그 추출 3) 해시태그 입력(반복)
		// 해시태그 추출 알고리즘
		// # #구디 #자바 등
		HashtagDao hDao = new HashtagDao();
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
		// 리다이렉트 > cashbookOne
		response.sendRedirect(request.getContextPath()+"/calendarOne?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}

}
