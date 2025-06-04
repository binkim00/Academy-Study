<%@page import="utils.JSFunction"%>
<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDAO dao = new BoardDAO();
	int result = dao.deleteBoard(num);
	if(result == 1){
		response.sendRedirect("board_list.jsp");
	}else{
		JSFunction.alertBack("삭제가 실패했습니다.", out);
	}
	
%>