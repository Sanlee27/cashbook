package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;

@WebServlet("/removeCashbook")
public class RemoveCashbookController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(cashbookNo + "+ cashbookNo");
		
		CashbookDao dao = new CashbookDao();
		
		int row = dao.removeOneByNo(cashbookNo);
		
		if(row != 1) {
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		} 
		
		/*
		 * System.out.println("삭제성공");
		 * response.sendRedirect(request.getContextPath()+"/calendarOne?targetYear="+
		 * targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
		 */
		
		String successMessage = "삭제 성공";
	    String redirectUrl = request.getContextPath() + "/calendarOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate;
	    String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
	    String encodedScript = "alert(decodeURIComponent('" + encodedMessage + "')); window.location.replace('" + redirectUrl + "');";
	    String script = "<script>" + encodedScript + "</script>";
	    response.getWriter().write(script);
	}

}
