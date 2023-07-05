package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/memberOne") // 상세정보 > get요청
public class MemberOneController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		// System.out.println(loginMember);
		
		// 모델 값 구하기(dao 메소드 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		// System.out.println(member);
		// member를 출력하는(포워딩 대상) memberOne.jsp에도 공유 되어야됨.
		// request가 이미 공유됨으로, request안에 넣어서 공유하면 됨
		request.setAttribute("member", member);
		
		// memberOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

}
