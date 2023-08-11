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

@WebServlet("/removeMember")
public class RemoveMemberController extends HttpServlet {
	// 비밀번호 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		System.out.println(loginMember);
		
		// 모델 값 구하기(dao 메소드 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		// System.out.println(member);
		// member를 출력하는(포워딩 대상) memberOne.jsp에도 공유 되어야됨.
		// request가 이미 공유됨으로, request안에 넣어서 공유하면 됨
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	
	// 탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		// System.out.println(loginMember + "loginMember");
		
		String memberId = loginMember.getMemberId();
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId);
		System.out.println(memberPw);
		
		MemberDao dao = new MemberDao();
		int row = dao.removeMember(memberId, memberPw);
		if(row == 0) {
			System.out.println("탈퇴실패");
			String msg = "비밀번호를 확인하세요..";
			response.sendRedirect(request.getContextPath()+"/removeMember?msg=" + URLEncoder.encode(msg, "UTF-8"));
			return;
		}
		// 탈퇴성공
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/login"); 
	}
}
