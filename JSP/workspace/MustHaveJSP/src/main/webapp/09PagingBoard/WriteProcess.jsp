<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
// title과 content를  변수에 저장
String title = request.getParameter("title");
String content = request.getParameter("content");

// dto 생성
BoardDTO dto = new BoardDTO();
// title, content, id  데이터를 설정
dto.setTitle(title);
dto.setContent(content);
dto.setId(session.getAttribute("UserId").toString());

BoardDAO dao = new BoardDAO();
/* int iResult = dao.insertWrite(dto); */
int iResult = 0;
for(int i=1; i<=100; i++){
	dto.setTitle(title + "-" + i);
	dto.setContent(content + "-" + i);
	iResult = dao.insertWrite(dto);
}
dao.close();

// iResult가1이면 정상적으로 데이터가 저장됨
// 다른 숫자가 나오면 비정상으로 데이터가 저장되지않음을 의미
if(iResult == 1){
	response.sendRedirect("List.jsp");
}else{
	JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}
%>








