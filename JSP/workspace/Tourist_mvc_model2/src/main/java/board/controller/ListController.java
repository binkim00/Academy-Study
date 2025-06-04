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
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// BoardDTO를 사용한 리스트 boardLists를 선언
		List<BoardDTO> boardLists = new ArrayList<>();
		// map에 검색조건 설정하기
		String searchWord = req.getParameter("searchWord");
		Map<String,Object> map = new HashMap<>();
		if(searchWord != null && !searchWord.equals("")) {
			map.put("searchWord", searchWord);
		}
		// tourist_board의 전체 건수 저장하기
		BoardDAO dao = new BoardDAO();
		int totalCount = dao.selectCount(map);
		// DAO를 사용하여 DB에서 tourist_board의 전체 데이터를 리스트에 저장
		boardLists = dao.selectList(map);
		// req에 boardLists데이터, 전체 건수 저장하기
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("totalCount", totalCount);
		// board_list.jsp 실행
		req.getRequestDispatcher("/board_list2.jsp").forward(req, resp);
	}
}











