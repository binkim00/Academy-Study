package model2.mvcboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
//	service 메서드 : doGet,doPost가 실행되기 전 실행되는 메서드
//	get,post 상관없이 실행할 내용이나 doGet,doPost실행전 실행할 내용이 있는 경우 작성
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MVCBoardDAO dao = new MVCBoardDAO();
		String idx = req.getParameter("idx");
		// 조회수 1 증가
		dao.updateVisitCount(idx);
		// idx를 기준으로한 데이터 dto에 저장
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close();
		// 엔터키를 br태그로 변환
		dto.setContent(dto.getContent().replaceAll("(\r\n|\r|\n)", "<br/>"));
		
		String ext = null;
		// 날짜형식의 파일이름을 변수 저장
		String fileName = dto.getSfile();
		
		if(fileName!=null) {
			// 확장자를 구하기 위한 부분
			// 마지막 . 뒤에있는 글자부터 마지막까지 자르면 확장자가 저장됨
			ext = fileName.substring(fileName.lastIndexOf(".")+1);
		}
		// 이미지의 경우 화면에서 보여주기 위해서
		String[] mimeStr = {"png","jpg","gif"};
		// 이미지 확장자가 저장된 List를 생성
		List<String> mimeList = Arrays.asList(mimeStr);
		boolean isImage = false;
		// 저장된 파일의 확장자가 List안에 존재하는지 확인
		if(mimeList.contains(ext)) {
			isImage = true;
		}
		// request에 게시글 데이터를 저장
		req.setAttribute("dto", dto);
		// request에 이미지 존재 여부를 boolean 값으로 저장
		req.setAttribute("isImage", isImage);
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp);
	}

}








