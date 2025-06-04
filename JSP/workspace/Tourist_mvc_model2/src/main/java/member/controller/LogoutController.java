package member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// request에 있는 세션을 꺼내어 저장
		HttpSession session = req.getSession();
		// session에 저장된 데이터를 하나씩 삭제하는 메서드
		session.removeAttribute("UserId");
		session.removeAttribute("userDTO");
		// session에 저장된 데이터를 전체 삭제
		session.invalidate();
		// 로그아웃 처리 후 실행할 페이지
		resp.sendRedirect("/Tourist_mvc_model2/");
	}
}








