package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

@WebServlet("/mvcboard/write.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 1
)
public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//화면에 출력할 데이터가 없기 때문에 바로 forward로 JSP파일을 실행함 
		req
		.getRequestDispatcher("/14MVCBoard/Write.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Uploads폴더 위치 설정
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		String originalFileName = "";
		try {
			// 원본 파일 이름 설정 및 Uploads폴더에 실제 파일을 저장
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		}catch(Exception e) {
			// 서블릿의 경우 response에 출력할 데이터를 설정
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../mvcboard/write.do");
			return;
		}
		// 게시글 DTO 설정 : 화면에서 보내준 데이터
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(req.getParameter("name"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setPass(req.getParameter("pass"));
		// 첨부파일이 있을 경우 첨부파일의 데이터를 저장
		if(originalFileName != "") {
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			// 원본 파일 이름
			dto.setOfile(originalFileName);
			// 날짜형식 파일 이름
			dto.setSfile(savedFileName);
		}
		
		MVCBoardDAO dao = new MVCBoardDAO();
		// DAO의 INSERT문을 실행하여 DB에 데이터를 저장
		int result = dao.insertWrite(dto);
		dao.close();
		
		if(result == 1) {
			// 정상처리가 되었으면 List화면을 출력
			resp.sendRedirect("../mvcboard/list.do");
		}else{
			// 실패시 처리
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "../mvcboard/write.do");
		}
		
			
	}
	
}

















