package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardlist.do")
public class ListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchWord = req.getParameter("searchWord");
        Map<String, Object> map = new HashMap<>();
        if (searchWord != null && !searchWord.equals("")) {
            map.put("searchWord", searchWord);
        }

        BoardDAO dao = new BoardDAO();
        int totalCount = dao.selectCount(map);

        String pageNumStr = req.getParameter("pageNum");
        int currentPage = (pageNumStr != null) ? Integer.parseInt(pageNumStr) : 1;
        int pageSize = 10;
        int start = (currentPage - 1) * pageSize + 1;
        int end = currentPage * pageSize;

        map.put("start", start);
        map.put("end", end);

        List<BoardDTO> boardLists = dao.selectListPaging(map);

        req.setAttribute("boardLists", boardLists);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("pageNum", currentPage);

        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
        req.setAttribute("totalPage", totalPage);

        req.getRequestDispatcher("/board_list2.jsp").forward(req, resp);
    }
}
