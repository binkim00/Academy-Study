<%@page import="java.util.Date"%> 
<!-- Date 클래스 (시간 관련) -->
<%@page import="java.text.SimpleDateFormat"%>
<!-- 날짜 형식을 포맷팅하는 클래스 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    // ▶ 시간 포맷을 "시:분:초" 형식으로 설정
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
    // ▶ 세션이 처음 생성된 시각 (로그인한 시점)
	long creationTime = session.getCreationTime();
	String creationTimeStr = dateFormat.format(new Date(creationTime));
	
    // ▶ 마지막으로 요청한 시각 (마지막 활동 시점)
	long lastTime = session.getLastAccessedTime();
	String lastTimeStr = dateFormat.format(new Date(lastTime));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SessionMain</title>
</head>
<body>
	<h2>Session 설정 확인</h2>
	<ul>
		<!-- 세션이 몇 초 동안 유지되는지 (기본: 1800초 = 30분) -->
		<li>세션 유지 시간 : <%=session.getMaxInactiveInterval() %></li>

		<!-- 현재 클라이언트의 세션 ID (JSESSIONID) -->
		<li>세션 아이디 : <%=session.getId() %></li>

		<!-- 최초 세션이 만들어진 시각 -->
		<li>최초 요청 시간 : <%=creationTimeStr %></li>

		<!-- 마지막으로 요청을 보낸 시각 -->
		<li>마지막 요청 시간 : <%=lastTimeStr %></li>
	</ul>
</body>
</html>








