package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// hidden데이터 idx, mode데이터를 변수에 저장
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		// 비밀번호 데이터 저장
		String pass = req.getParameter("pass");
		
		MVCBoardDAO dao = new MVCBoardDAO();
		// DB에서 글번호와 비밀번호가 일치하는지 확인
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		// 비밀번호가 일치하면 실행하는 if문
		if(confirmed) {
			// 수정하기 버튼으로 접속했을 경우
			if(mode.equals("edit")) {
				HttpSession session = req.getSession();
				// 세션에 게시글 비밀번호 저장
				session.setAttribute("pass", pass);
				resp.sendRedirect("../mvcboard/edit.do?idx="+idx);
			// 삭제 버튼으로 접속했을 경우
			}else if(mode.equals("delete")){
				// 위에서 dao.close()를 실행했기 때문에 다시 인스턴스를 생성
				dao = new MVCBoardDAO();
				// 삭제할 파일 확인하기 위한 SELECT문 실행
				MVCBoardDTO dto = dao.selectView(idx);
				// 게시글 삭제 실행
				int result = dao.deletePost(idx);
				dao.close();
				// 정상적으로 삭제되면 파일을 삭제
				if(result == 1) {
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(req, "/Uploads", saveFileName);
				}
				JSFunction.alertLocation(resp, "삭제되었습니다.", "../mvcboard/list.do");
			}
		} else {
			// 비밀번호가 일치하지 않으면 실행하는 else문
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
	}
	
}











