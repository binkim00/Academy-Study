package member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

@WebServlet("/join.do")
public class JoinController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/join.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("id"));
		dto.setEmail(req.getParameter("email"));
		dto.setName(req.getParameter("name"));
		dto.setPassword(req.getParameter("password"));
		dto.setPhone(req.getParameter("phone"));
		dto.setGender(req.getParameter("gender"));
		String agree = req.getParameter("agree");

		if(agree != null && agree.equals("on")) {
			dto.setAgree(true);
		}else {
			dto.setAgree(false);
		}
		dto.setContent(req.getParameter("content"));
		
		MemberDAO dao = new MemberDAO();
		dao.insertMember(dto);
		dao.close();
		resp.sendRedirect(req.getContextPath() + "/login.do");
	}
}










