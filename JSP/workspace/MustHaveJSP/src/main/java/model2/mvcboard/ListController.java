package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

@WebServlet("/mvcboard/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// 검색 조건 설정
		Map<String ,Object> map = new HashMap<>();
		// 제목이나 내용으로 검색하는 설정
		String searchField = req.getParameter("searchField");
		// 검색어 설정
		String searchWord = req.getParameter("searchWord");
		if(searchWord != null) {
			map.put("searcField", searchField);
			map.put("searchWord", searchWord);
		}

		ServletContext application = getServletContext();
		// Web.xml의 페이지당 데이터 개수
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		// web.xml의 한번 출력할 페이지 개수
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		int pageNum = 1;
		// 현재 클릭한 페이지 데이터
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}
		// 1페이지는 1~10, 2페이지는 11~20 방식으로 페이지의 데이터를 받아오는 부분
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
		map.put("start", start);
		map.put("end", end);
		
		// 게시글 데이터 저장
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		// 게시글의 총 개수 저장
		int totalCount = dao.selectCount(map);
		
		// 페이징 처리
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// List.jsp 페이지로 데이터를 전달
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		// forward를 이용하여 List.jsp페이지를 실행
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
		
		dao.close();
	}
}























