<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		try{
			int myAge = Integer.parseInt(request.getParameter("age"))+10;
			out.println("10년 후 당신의 나이는"+myAge+" 입니다.");
		}catch(Exception e){
			out.println("요청하신 페이지를 찾을 수 없습니다.");
		}
	%>
	<!-- isErrorPage="true": 이 페이지는 예외가 전달되는 전용 페이지임
	exception 객체를 자동으로 사용할 수 있음 (JSP 내장 객체 중 하나)
	예외 메시지를 사용자에게 출력해줌 -->
	<!-- 
	트라이 캐치문을 지우면 에러코드 뛰우는 화면 출력가능(Exception.jsp파일 예시 보는 방법)
	 -->
</body>
</html>









