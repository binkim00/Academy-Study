<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%-- 
    page 지시어:
    - language="java"         → JSP가 사용할 언어 지정 (대부분 Java)
    - contentType             → 클라이언트로 보낼 콘텐츠 타입 및 문자셋
    - pageEncoding            → JSP 파일 자체의 인코딩 방식
    - trimDirectiveWhitespaces → true로 설정하면 JSP가 생성한 HTML 코드의 공백을 줄여줌
--%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%-- 
    import 지시어:
    - java.util.Date           → 현재 날짜와 시간을 위한 클래스
    - java.text.SimpleDateFormat → 날짜 형식을 지정해서 보기 좋게 출력하는 클래스
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - import 속성</title>
</head>
<body>
	<%
		// 현재 날짜와 시간 객체 생성
		Date today = new Date();

		// 날짜 형식 지정 (예: 2025-05-12 형식으로 출력)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// 날짜 객체를 문자열로 변환
		String todayStr = dateFormat.format(today);

		// out 객체는 JSP 내장 객체로, 브라우저에 HTML을 출력할 수 있음
		out.println("오늘 날짜 : " + todayStr);
	%>
</body>
</html>
