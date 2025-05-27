package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 어노태이션을 이용한 Servlet 맵핑하기
@WebServlet("/12Servlet/HelloServlet.do")
public class HelloServlet extends HttpServlet{
	// 서블릿 버전관리용 식별자, 작성하지않으면 경고가 표시되지만 실행에는 상관 없음
	private static final long serialVersionUID = 1L;
	
	// doGet() : Get방식으로 전달된 요청을 처리하는 메서드, 화면출력
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("message", "Hello Servlet..!!");
		req.getRequestDispatcher("/12Servlet/HelloServlet.jsp")
		.forward(req, resp);
	}
	// doPost() : post방식으로 전달된 요청을 처리하는 메서드, 추가,수정,삭제용도
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}










