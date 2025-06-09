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

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id, pw);

		if(dto.getId() != null && dto.getId().equals(id)) {
			HttpSession session = req.getSession();
			session.setAttribute("UserId", id);
			session.setAttribute("userDTO", dto);
			resp.sendRedirect(req.getContextPath() + "/");
		}else {
			req.setAttribute("loginErrMsg", "아이디나 비밀번호를 확인해주세요.");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}










