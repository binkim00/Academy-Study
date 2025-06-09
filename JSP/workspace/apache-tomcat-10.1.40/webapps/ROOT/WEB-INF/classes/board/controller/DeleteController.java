package board.controller;

import java.io.IOException;

import board.dao.BoardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boarddelete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String numStr = req.getParameter("num");
		int num = Integer.parseInt(numStr);

		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(num);
		dao.close();

		resp.sendRedirect("boardlist.do");
	}
}
