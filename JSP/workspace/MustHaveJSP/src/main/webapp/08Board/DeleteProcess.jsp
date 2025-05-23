<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
// 삭제할 게시글의 번호를 저장
String num = request.getParameter("num");
BoardDTO dto = new BoardDTO();
BoardDAO dao = new BoardDAO();
// 게시글의 번호로 DB에서 데이터를 검색하여 저장 - 게시글의 ID를 얻기 위해서
dto = dao.selectView(Integer.parseInt(num));
// 현재 로그인한 계정의 ID를 저장
String sessionId = session.getAttribute("UserId").toString();
// 삭제의 성공 실패의 결과를 저장하는 변수
int delResult = 0;
// 로그인한 ID와 게시글을 작성한 ID가 같은지 확인
if(sessionId.equals(dto.getId())){
	// dto에 게시글 번호를 저장 - 확인용
	dto.setNum(Integer.parseInt(num));
	// DAO를 이용하여 DELETE문을 실행
	delResult = dao.deletePost(dto);
	if(delResult == 1){
		// 삭제가 성공하면 List페이지로 이동
		JSFunction.alertLocation("삭제되었습니다.", "List.jsp", out);
	}else{
		// 삭제가 실패하면 뒤로가기를 실행
		JSFunction.alertBack("삭제에 실패하였습니다.", out);
	}
}else{
	// 게시글의 ID와 로그인한 ID가 다를 경우 뒤로가기 실행
	JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);
	return;
}

%>








