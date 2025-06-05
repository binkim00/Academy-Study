<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forward 실행</title>
</head>
<body>
	<h2>forward를 이용한 request 영역의 속성값 읽기</h2>
	<%
		// [1] 이전 페이지에서 전달받은 Person 객체 꺼내기
		Person rPerson = (Person)(request.getAttribute("requestPerson"));
	%>
	<ul>
		<!-- [2] 이전 페이지에서 삭제 안된 속성만 출력됨 -->
		<li>String객체 : <%=request.getAttribute("requestString") %></li> <!-- null -->
		<li>Person객체 : <%=rPerson.getName() %>, <%=rPerson.getAge() %>
	</ul>

	<h2>매개변수로 전달된 값 출력하기</h2>
	<%
		// [3] URL에 포함된 파라미터 값 읽기
		request.setCharacterEncoding("UTF-8");  // 한글 처리
		out.print(request.getParameter("han")); // '한글'
		out.print(request.getParameter("eng")); // '영어'
	%>
</body>
</html>
