<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - ResponseLogin</title>
</head>
<body>

<%
	// 사용자 입력값 받기 (아이디, 비밀번호)
	String id = request.getParameter("user_id");
	String pw = request.getParameter("user_pw");

	// 로그인 성공 조건: 아이디 must, 비번 1234
	if (id.equalsIgnoreCase("must") && pw.equalsIgnoreCase("1234")) {
		// sendRedirect: 새 요청을 보내기 때문에 request/response 공유 안 됨
		// 주소창이 ResponseWelcome.jsp로 변경됨
		response.sendRedirect("ResponseWelcome.jsp");
	} else {
		// forward: 기존 요청을 그대로 다른 페이지로 넘김 (request 유지됨)
		// 쿼리스트링으로 loginErr=1 전달
		request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
		       .forward(request, response);
	}
%>

</body>
</html>
