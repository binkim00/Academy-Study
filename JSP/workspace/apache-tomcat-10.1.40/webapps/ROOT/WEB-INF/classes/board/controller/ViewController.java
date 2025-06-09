package board.controller;

import java.io.IOException;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardview.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String numStr = req.getParameter("num");
		int num = 0;
		
		try {
			num = Integer.parseInt(numStr);
		}catch(NumberFormatException e) {
			resp.sendRedirect("boardlist.do");
			return;
		}
		
		BoardDAO dao = new BoardDAO();
		dao.updateVisitCount(num);
		BoardDTO dto = dao.selectView(num);
		dao.close();
		
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("board_view_jstl.jsp");
		dispatcher.forward(req, resp);
		
	}
}
