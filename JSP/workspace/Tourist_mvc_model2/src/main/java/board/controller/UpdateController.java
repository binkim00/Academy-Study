package board.controller;

import java.io.IOException;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardupdate.do")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String num = req.getParameter("num");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(num));
		dto.setTitle(title);
		dto.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoard(dto);
		dao.close();
		
		resp.sendRedirect("boardview.do?num=" + num);
		
	}

}
