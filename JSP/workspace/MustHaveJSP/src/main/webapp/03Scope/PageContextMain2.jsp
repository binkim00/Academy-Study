<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person" %>
<%
// 일반 변수로 선언
int pInteger = 1002;
String pString = "페이지 영역의 문자열2";
Person pPerson = new Person("한석봉",992);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 영역 속성값 읽기2</title>
</head>
<body>
	<h2>page 영역 속성값 읽기2</h2>
	<ul>
		<li>Integer 객체2 : <%=pInteger %></li>
		<li>String 객체2 : <%=pString %></li>
		<li>Person 객체2 : <%=pPerson.getName() %>,<%=pPerson.getAge()%></li>
	</ul>
	<h2>include된 파일에서 page 영역 읽어오기2</h2>
	<%@ include file="PageInclude2.jsp" %>
	<h2>페이지 이동 후 page 영역 읽어오기2</h2>
	<a href="PageLocation.jsp">PageLocation.jsp</a>
</body>
</html>













