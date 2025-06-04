package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/13FileUpload/MultipleProcess.do")
@MultipartConfig(
	maxFileSize = 1024*1024*1,
	maxRequestSize = 1024*1024*10
)
public class MultipleProcess extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			// 여러개의 파일이름을 List에 저장, 실제 파일도 저장
			ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory);
			for(String originalFileName : listFileName) {
				// 파일이름을 날짜형식으로 변경
				String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
				// 데이터베이스 파일 데이터를 저장
				insertMyFile(req, originalFileName, savedFileName);
			}
			// FileList.jsp화면 실행
			resp.sendRedirect("FileList.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("MultiFileUploadMain.jsp").forward(req, resp);
		}
	}
	private void insertMyFile(HttpServletRequest req,String oFileName, String sFileName) {
		// 타이틀 데이터 
		String title = req.getParameter("title");
		// 카테고리를 하나의 문자열로 저장
		String [] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if(cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		}else {
			for(String s : cateArray) {
				cateBuf.append(s+", ");
			}
		}
		// 파일에 대한 DTO생성
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		// 파일 데이터를 DB에 저장
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
	}
}












