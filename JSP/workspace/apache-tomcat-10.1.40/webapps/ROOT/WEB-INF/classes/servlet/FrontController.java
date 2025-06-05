package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// *.one이 주소 제일 뒤에 있으면 현재 서블릿을 실행하도록 설정
@WebServlet("*.one")
public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		int lastSlash = uri.lastIndexOf("/");
		String commandStr = uri.substring(lastSlash);
		if(commandStr.equals("/regist.one")) {
			registFunc(req);
		}else if(commandStr.equals("/login.one")) {
			loginFunc(req);
		}else if(commandStr.equals("/freeboard.one")) {
			freebordFunc(req);
		}
		req.setAttribute("uri", uri);
		req.setAttribute("commandStr", commandStr);
		req.getRequestDispatcher("/12Servlet/FrontController.jsp")
		.forward(req, resp);
	}
	void registFunc(HttpServletRequest req) {
		req.setAttribute("resultValue", "<h4>회원가입</h4>");
	}
	void loginFunc(HttpServletRequest req) {
		req.setAttribute("resultValue", "<h4>로그인</h4>");	
	}
	void freebordFunc(HttpServletRequest req) {
		req.setAttribute("resultValue", "<h4>자유게시판</h4>");
	}
	
}









