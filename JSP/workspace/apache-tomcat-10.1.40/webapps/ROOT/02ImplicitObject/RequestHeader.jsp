<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Enumeration" %>
<%-- 
  - JSP 설정: 자바 사용, UTF-8 문자 인코딩
  - import: Enumeration (열거형 객체, 반복문처럼 쓰임)
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - request</title>
</head>
<body>

<h2>3. 요청 헤더 정보 출력하기</h2>

<% 
	// 1. 모든 요청 헤더 이름들을 Enumeration 객체로 가져옴
	Enumeration headers = request.getHeaderNames();

	// 2. while문으로 모든 헤더 이름을 순서대로 꺼냄
	while(headers.hasMoreElements()) {

		// 3. 하나의 헤더 이름 추출 (예: user-agent, host 등)
		String headerName = (String) headers.nextElement();

		// 4. 해당 헤더 이름으로 실제 값 조회
		String headerValue = request.getHeader(headerName);

		// 5. 결과 출력
		out.print("헤더명 : " + headerName + ", 헤더값 : " + headerValue + "<br/>");
	}
%>

<p>※ 이 파일을 직접 주소창에 입력해서 실행하면 referer 헤더는 출력되지 않습니다.</p>
<%-- referer는 링크 클릭으로 넘어올 때만 포함됨 --%>

</body>
</html>
