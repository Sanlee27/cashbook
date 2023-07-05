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
import cash.vo.Cashbook;
import cash.vo.Member;

/**
 * Servlet implementation class CashbookListController
 */
@WebServlet("/cashbookListByTag")
public class CashbookListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 구현
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		CashbookDao dao = new CashbookDao();
		
		// ============== 페이지 ================
		// 아이디/태그 저장
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		String memberId = loginMember.getMemberId();
		String word = request.getParameter("word");
		
		// 페이지
		// 현재 페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 페이지 당 로우(내역) 개수
		int rowPerPage = 5;
		if(request.getParameter("rowPerPage") != null) {
		      rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		// 총 로우 개수(아이디 별 해당 태그)
		int totalRow = dao.listByTagCnt(memberId, word);
		System.out.println(totalRow + " : totalRow");
		// 페이지별 시작행
		int beginRow = (currentPage-1) * rowPerPage;
		// 페이지별 마지막행
		int endRow = beginRow + (rowPerPage - 1);
		if(endRow > totalRow){
			endRow = totalRow;
		}
		System.out.println(endRow + " : endRow");
		// 페이지 선택버튼
		int pagePerPage = 5;
		// 마지막 페이지
		int lastPage = totalRow / rowPerPage; 
		if(totalRow % rowPerPage != 0){
			lastPage = lastPage + 1;
		}
		System.out.println(lastPage + " : lastPage");
		// 페이지 선택 버튼 최소값
		int minPage = (((currentPage-1) / pagePerPage) * pagePerPage) + 1;
		System.out.println(minPage + " : minPage");
		// 페이지 선택 버튼 최대값
		int maxPage = minPage + (pagePerPage - 1);
		if(maxPage > lastPage){ // ex) lastPage는 27, maxPage가 30(21~30) 일 경우
			maxPage = lastPage;  // maxPage를 lastPage == 27로 한다. 
		}
		System.out.println(maxPage + " : maxPage");
		// ====================================
		
		List<Cashbook> list = dao.selectCashbookListByTag(memberId, word, beginRow, rowPerPage);
		
		request.setAttribute("list", list);
		
		request.setAttribute("word", word);
		request.setAttribute("memberId", memberId);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("pagePerPage", pagePerPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("minPage", minPage);
		request.setAttribute("maxPage", maxPage);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}
}
