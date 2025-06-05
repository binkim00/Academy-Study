<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="IncludeFile.jsp" %>
<!-- 특징
파일을 컴파일 전에 합쳐서 하나로 만듦
마치 복붙처럼 전체 소스가 한 파일에 들어간다
변수나 선언부도 공유됨 
페이지 상단의 공통 메뉴, 공통 헤더 등에 자주 사용됨 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 지시어 사용하기</title>
</head>
<body>
	<%
		out.println("오늘 날짜" + toDay);
		out.println("<br/>");
		out.println("내일 날짜" + tomorrow);
	%>
</body>
</html>












