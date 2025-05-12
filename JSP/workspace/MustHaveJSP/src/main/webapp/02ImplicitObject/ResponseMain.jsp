<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - response</title>
</head>
<body>

	<h2>1. 로그인 폼</h2>

	<%
		// 로그인 실패 시 메시지 출력 (쿼리스트링으로 전달됨)
		String loginErr = request.getParameter("loginErr");
		if (loginErr != null) {
			out.print("로그인 실패");  // 실패 메시지 표시
		}
	%>

	<form action="./ResponseLogin.jsp" method="post">
		아이디 : <input type="text" name="user_id" /><br/>
		패스워드 : <input type="password" name="user_pw"/> <br/>
		<input type="submit" value="로그인"/>
	</form>

	<h2>2. HTTP 응답 헤더 설정하기</h2>

	<!-- 응답 헤더 설정을 위한 입력 폼 -->
	<form action="./ResponseHeader.jsp" method="post">
		날짜 형식 : <input type="text" name="add_date" value="2021-10-25 09:00"/><br/>
		숫자 형식 : <input type="text" name="add_int" value="8282" /><br/>
		문자 형식 : <input type="text" name="add_str" value="홍길동"/><br/>
		<input type="submit" value="응답 헤더 설정 & 출력" />
	</form>

</body>
</html>
