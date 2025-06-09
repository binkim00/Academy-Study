package board.controller;

import java.io.IOException;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/boardwrite.do")
public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId") == null){
			JSFunction.alertLocation(resp, "로그인 후 이용해주십시오",
					"./login.do");
			return;
		}
		req.getRequestDispatcher("/board_write.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// dto 생성
		BoardDTO dto = new BoardDTO();
		// title, content, id  데이터를 설정
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		HttpSession session = req.getSession();
		dto.setId(session.getAttribute("UserId").toString());

		BoardDAO dao = new BoardDAO();
		int iResult = dao.insertWrite(dto);
		dao.close();

		// iResult가1이면 정상적으로 데이터가 저장됨
		// 다른 숫자가 나오면 비정상으로 데이터가 저장되지않음을 의미
		if(iResult == 1){
			resp.sendRedirect(req.getContextPath() + "/boardlist.do");
		}else{
			JSFunction.alertBack(resp,"글쓰기에 실패하였습니다.");
		}
	}
}












