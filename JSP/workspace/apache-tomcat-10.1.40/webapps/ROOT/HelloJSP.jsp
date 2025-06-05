<!-- 디렉티브(지시어) 태그 : JSP의 각종 설정을 하는 태그 
	page : JSP페이지 정보 설정, 언어 및 컨텐츠 타입, 인코딩을 설정-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바언어를 html과 함께 쓰도록 하는 부분 -->
<!-- <%! %> 선언부(Declation) : 멤버변수나 메서드를 선언하는 부분 -->
<%!
	//JSP태그 안에서의 주석처리 : //, /* */ 자바랑 같음
	String str1 = "JSP";
	String str2 = "안녕하세요!!";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloJSP</title>
</head>
<body>
	<%-- <%=str1 %> 표현식(Expression) : 변수나 반환값이 있는 메서드를 실행하는 부분 --%>
	<!-- out.print() 의 형식으로 변환되기 때문에 매개변수의 문법에 맞춰서 작성-->
	<h2>처음 만들어보는 <%=str1%></h2>
	<p>
		<%-- <% %> 스크립틀릿: 코드실행부 , 반복문,제어문등을 실행하는 자바 코드 작성  --%>
		<% out.println(str2+str1+"입니다. 열공합시다^^*"); %>
	</p>
</body>
</html>













