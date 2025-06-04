<%@page import="member.dto.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request 안에 들어있는 데이터를 DTO에 설정
	MemberDTO dto = new MemberDTO(
			request.getParameter("id"),
			request.getParameter("email"),
			request.getParameter("name"),
			request.getParameter("password"),
			request.getParameter("phone"),
			request.getParameter("gender"),
			request.getParameter("agree")==null?false:true ,
			request.getParameter("content")
	);
	MemberDAO dao = new MemberDAO();
	dao.insertMember(dto);
	response.sendRedirect("index.jsp");
%>








