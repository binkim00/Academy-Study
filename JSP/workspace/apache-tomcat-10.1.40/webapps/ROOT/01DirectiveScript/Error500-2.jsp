<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="IsErrorPage.jsp" %>
<!-- 에러 발생시 errorPage속성에 설정한 페이지로 이동하게됨 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - errorPage, isErrorPage 속성</title>
</head>
<body>
	<%
		// 에러 발생시 JSP에서 지원하는 에러 페이지가 출력되며 에러의 내용 및 원인을 모두 출력한다.
		// JSP에러페이지를 출력하지 않으려면 try/catch문 혹은 errorPage설정을 해야합니다.
		int myAge = Integer.parseInt(request.getParameter("age"))+10;
		out.println("10년 후 당신의 나이는"+myAge+" 입니다.");
	%>
	<!-- int a = 10 / 0; → ArithmeticException 예외 발생
	errorPage="Error500.jsp" 설정 덕분에 에러가 발생해도 사용자에게는 Error500.jsp로 이동됨
	사용자는 예외 페이지만 보게 됨 (브라우저가 안 터짐) -->
</body>
</html>









