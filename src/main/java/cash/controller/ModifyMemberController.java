package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/modifyMember")
public class ModifyMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		// System.out.println(loginMember);
		
		// 모델 값 구하기(dao 메소드 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		// System.out.println(member);
		// member를 출력하는(포워딩 대상) memberOne.jsp에도 공유 되어야됨.
		// request가 이미 공유됨으로, request안에 넣어서 공유하면 됨
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		String memberId = loginMember.getMemberId();
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId);
		System.out.println(memberPw);
		
		MemberDao dao = new MemberDao();
		
		int ckPw = dao.checkPw(memberId, memberPw);
		System.out.println(ckPw + "ckPw");
		
		// 비밀번호 일치 시 alert와 같이 redirect
		if(ckPw == 1) {
			System.out.println("비밀번호가 이전과 일치합니다.");
			String msg = "비밀번호가 이전과 일치합니다.";
			response.sendRedirect(request.getContextPath()+"/modifyMember?msg=" + URLEncoder.encode(msg, "UTF-8"));
			return;
		}
		
		int row = dao.modifyMember(memberId, memberPw);
		System.out.println(row + " << row");
		if(row == 0) {
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/modifyMember");
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/memberOne");
	}

}
