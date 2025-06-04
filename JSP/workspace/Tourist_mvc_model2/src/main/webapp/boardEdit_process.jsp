<%@page import="utils.JSFunction"%>
<%@page import="board.dao.BoardDAO"%>
<%@page import="board.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String title = request.getParameter("title");
String content = request.getParameter("content");

BoardDTO dto = new BoardDTO();
dto.setNum(num);
dto.setTitle(title);
dto.setContent(content);

BoardDAO dao = new BoardDAO();
int result = dao.updateBoard(dto);
if(result == 1){
	response.sendRedirect("board_view.jsp?num="+dto.getNum());
}else{
	JSFunction.alertBack("수정에 실패했습니다.", out);
}

%>







