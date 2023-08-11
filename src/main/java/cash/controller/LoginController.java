package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		/* cash.vo.Member에 using Field 생성해둬서 이렇게 사용 */
		Member member = new Member(memberId, memberPw, null, null); 
		
		MemberDao memberDao  = new MemberDao();
		Member loginMember = memberDao.selectMemberById(member);
		
		// alert 띄우게하기
		if (loginMember == null) {
		    System.out.println("로그인 실패");
		    String msg = "아이디 혹은 비밀번호를 확인하세요.";
		    response.sendRedirect(request.getContextPath() + "/login?msg=" + URLEncoder.encode(msg, "UTF-8"));
		    return;
		}
		
		// 로그인 성공시 : session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", loginMember);
		response.sendRedirect(request.getContextPath()+"/cashbook");
	}

}
