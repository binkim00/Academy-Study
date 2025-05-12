<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - request</title>
</head>
<body>

<%
	// POST 방식으로 보낼 경우 한글 처리를 위해 UTF-8로 인코딩 설정
	request.setCharacterEncoding("UTF-8");

	// 단일 값 읽기: id와 성별
	String id = request.getParameter("id");            // 아이디
	String gender = request.getParameter("gender");    // 성별

	// 다중 값 읽기: checkbox는 여러 개 선택되므로 배열로 받음
	// 체크박스의 경우 getParameterValues를 이용하여 String배열로 데이터를 받을 수 있음.
	String[] favo = request.getParameterValues("favo"); // 관심사항(경제, 정치, 연예 등)
	String favoStr = "";

	// 선택된 관심사항들을 하나의 문자열로 이어붙이기
	if(favo != null){
		for(int i=0; i< favo.length; i++){
			favoStr += favo[i] + " ";
		}
	}

	// 줄바꿈 문자(\r\n)를 <br/>로 바꿔서 HTML에서 줄바꿈이 되도록 처리
	String intro = request.getParameter("intro").replace("\r\n","<br/>");
%>

<!-- 출력 결과 HTML로 보여주기 -->
<ul>
	<li>아이디 : <%= id %></li>
	<li>성별 : <%= gender %></li>
	<li>관심사항 : <%= favoStr %></li>
	<li>자기소개 : <%= intro %></li>
</ul>

</body>
</html>
