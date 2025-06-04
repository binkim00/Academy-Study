package member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.dao.MemberDAO;
import member.dto.MemberDTO;
import utils.JSFunction;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp")
		.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//login.jsp화면에서 보내준 id와 pw를 저장
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		// id와 pw를 이용하여 회원 데이터를 취득
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id, pw);
		// Select문의 실행 결과로 회원 데이터가 있는지 확인하는 if문 
		if(dto.getId() != null && dto.getId().equals(id)) {
			// 세션에 id를 저장
			HttpSession session = req.getSession();
			session.setAttribute("UserId", id);
			session.setAttribute("userDTO", dto);
			// 로그인 성공 시 index 페이지로 이동
			resp.sendRedirect("/Tourist_mvc_model2/");
		}else {
			// id나 pw가 틀렸을 경우 에러메시지 설정
			req.setAttribute("loginErrMsg", "아이디나 비밀번호를 확인해주세요.");
			// 다시 로그인 페이지로 이동
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}










