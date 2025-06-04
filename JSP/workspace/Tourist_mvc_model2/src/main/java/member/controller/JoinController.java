package member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

@WebServlet("/join.do")
public class JoinController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실행할 JSP파일의 위치를 설정
		req.getRequestDispatcher("/join.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("id"));
		dto.setEmail(req.getParameter("email"));
		dto.setName(req.getParameter("name"));
		dto.setPassword(req.getParameter("password"));
		dto.setPhone(req.getParameter("phone"));
		dto.setGender(req.getParameter("gender"));
		String agree = req.getParameter("agree");
		// 개인정보 사용동의 데이터 저장 if문
		if(agree != null && agree.equals("on")) {
			// 체크박스에 체크했을때 true를 저장
			dto.setAgree(true);
		}else {
			// 체크하지 않았을때 false를 저장
			dto.setAgree(false);
		}
		dto.setContent(req.getParameter("content"));
		
		// DAO를 사용하여 DB에 Insert문 실행
		MemberDAO dao = new MemberDAO();
		dao.insertMember(dto);
		dao.close();
		// Insert가 완료되면 로그인 페이지로 이동
		// get 요청을 실하는것과 같은 처리 실행
		// 주소창에 적어서 실행하는 주소를 그대로 작성
		resp.sendRedirect("/Tourist_mvc_model2/login.do");
	}
}










