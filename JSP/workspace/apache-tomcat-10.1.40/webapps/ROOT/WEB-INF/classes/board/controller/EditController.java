package board.controller;

import java.io.IOException;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardedit.do")
public class EditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String numStr = req.getParameter("num");
        int num = Integer.parseInt(numStr);

        BoardDAO dao = new BoardDAO();
        BoardDTO dto = dao.selectView(num);
        dao.close();

        req.setAttribute("dto", dto);

        req.getRequestDispatcher("/board_edit.jsp").forward(req, resp);
    }
}
