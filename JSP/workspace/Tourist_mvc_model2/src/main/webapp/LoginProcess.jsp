<%@page import="member.dto.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDAO dao = new MemberDAO();
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	MemberDTO dto = dao.getMember(id, pw);
	if(dto.getId()!=null){
		session.setAttribute("userDTO",dto);
		session.setAttribute("UserId", id);
		response.sendRedirect("index.jsp");
	}else{
		request.setAttribute("loginErrMsg", "아이디나 비밀번호를 확인해주세요.");
		/* request.getRequestDispatcher("login.jsp").forward(request, response); */
		%>
		<jsp:forward page="login.jsp" />
	<%}
%>




