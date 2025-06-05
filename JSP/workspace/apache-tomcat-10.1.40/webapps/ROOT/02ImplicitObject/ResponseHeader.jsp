<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Collection" %>

<%
	// 문자열을 날짜로 변환
	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	long add_date = s.parse(request.getParameter("add_date")).getTime(); // 밀리초 단위 long

	// 문자열을 정수로 변환
	int add_int = Integer.parseInt(request.getParameter("add_int"));

	// 문자열은 그대로 저장
	String add_str = request.getParameter("add_str");

	// response 객체에 헤더 추가, 헤더에 데이터를 저장
	response.addDateHeader("myBirthday", add_date);   // 날짜형 헤더
	// 마이넘버라는 이름의 헤더에 8282, 1004 둘다 저장
	response.addIntHeader("myNumber", add_int);        // 정수형 헤더
	response.addIntHeader("myNumber", 1004);           // 동일 이름 헤더 추가됨
	// 마이 네임이라는 이름의 헤더에 홍길동과 손오공 둘다 저장
	response.addHeader("myName", add_str);             // 문자열 헤더
	response.addHeader("myName", "손오공");             // 중복 이름으로 추가됨
	// setHeader를 사용하여 데이터를 수정 할 수 있음.
	response.setHeader("myName", "전우치"); // 데이터를 수정해서 손오공이 나오지 않음
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - response data</title>
</head>
<body>
	<h2>응답 헤더 정보 출력하기</h2>

	<%
		// 모든 헤더 이름 가져오기
		Collection<String> headerNames = response.getHeaderNames();
		for (String hName : headerNames) {
			String hValue = response.getHeader(hName);  // 해당 헤더의 첫 번째 값
			// 겟헤더는 첫번째 데이터만 출력하기 때문에 8282랑 홍길동이 두번 출력됨
	%>
			<li><%= hName %> : <%= hValue %></li>
	<% } %>

	<%
		// 동일한 이름으로 여러 개 설정된 헤더는 getHeaders()로 전부 출력 가능
		// 겟헤더를 사용하여 헤더 안에 잇는 데이터를 저장
		Collection<String> myNumber = response.getHeaders("myNumber");
		// 반복문을 이용하여 각각의 데이터를 출력
		for (String myNum : myNumber) {
	%>
			<li>myNumber : <%= myNum %></li>
	<% } %>

</body>
</html>
