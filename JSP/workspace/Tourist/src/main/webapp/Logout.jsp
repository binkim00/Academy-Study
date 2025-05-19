<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.removeAttribute("userDTO"); // 또는 session.invalidate();
    response.sendRedirect("index.jsp"); // ✅ 로그아웃 후 홈으로 이동
%>
