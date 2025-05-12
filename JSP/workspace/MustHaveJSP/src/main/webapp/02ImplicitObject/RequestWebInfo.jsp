<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - request</title>
</head>
<body>

	<h1>1. 클라이언트와 서버의 환경정보 읽기</h1>

	<ul>
		<!-- 전송 방식(GET 또는 POST) 확인 -->
		<li>데이터 전송 방식 : <%= request.getMethod() %></li>

		<!-- 전체 URL (예: http://localhost:8080/myApp/RequestWebInfo.jsp) -->
		<li>URL(전체주소) : <%= request.getRequestURL() %></li>

		<!-- URI (예: /myApp/RequestWebInfo.jsp) -->
		<li>URI(불리는 파일 경로) : <%= request.getRequestURI() %></li>

		<!-- HTTP 프로토콜 버전 -->
		<li>프로토콜 : <%= request.getProtocol() %></li>

		<!-- 서버 컴퓨터 이름 (보통 localhost 또는 도메인명) -->
		<li>서버명 : <%= request.getServerName() %></li>

		<!-- 포트 번호 (예: 8080) -->
		<li>서버 포트 : <%= request.getServerPort() %></li>

		<!-- 접속한 클라이언트의 IP 주소 -->
		<li>클라이언트 IP 주소 : <%= request.getRemoteAddr() %></li>

		<!-- GET 방식일 경우 URL의 파라미터 문자열 -->
		<li>쿼리스트링(파라미터의 문자열) : <%= request.getQueryString() %></li>

		<!-- GET이든 POST든, 파라미터 이름으로 값 읽기 -->
		<li>전송된 값1 (영어): <%= request.getParameter("eng") %></li>
		<li>전송된 값2 (한글): <%= request.getParameter("han") %></li>
	</ul>

</body>
</html>
