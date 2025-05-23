<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
// Edit페이지의 form태그에서 전달한 데이터를 저장
String num = request.getParameter("num");
String title = request.getParameter("title");
String content = request.getParameter("content");
// DAO에서 사용할 DTO를 생성
BoardDTO dto = new BoardDTO();
//DTO에 num, title, content데이터를 저장
dto.setNum(Integer.parseInt(num));
dto.setTitle(title);
dto.setContent(content);
// DB에 SQL을 실행할 DAO설정
BoardDAO dao = new BoardDAO();
// UPDATE문 실행
int affected = dao.updateEdit(dto);
dao.close();

// UPDATE결과 1이면 정상 실행 그외의 숫자면 에러 발생
if(affected == 1){
	response.sendRedirect("View.jsp?num="+num);
}else{
	// 실패했다면 이전 페이지로 뒤로가기를 실행
	JSFunction.alertBack("수정하기에 실패했습니다.", out);
}

%>







