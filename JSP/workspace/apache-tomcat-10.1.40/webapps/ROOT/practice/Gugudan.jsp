<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
    page 지시어:
    - language="java" → JSP에서 사용할 언어를 지정 (Java)
    - contentType → 브라우저로 보낼 문서 형식과 문자 인코딩
    - pageEncoding → 이 JSP 파일 자체의 문자 인코딩 방식
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 출력하기</title>
</head>
<body>
	<!-- 여기부터는 HTML 문서의 실제 본문이 시작됨 -->

	<!-- 
		구구단 예시:
		<p>2 * 1 = 2</p>
		<p>2 * 2 = 4</p>
	-->

	<% for(int i=2; i<=9; i++){ %>
	<%-- 
		바깥 for문: 단(dan)을 의미 (2단부터 9단까지 반복)
		i는 2부터 9까지 하나씩 증가하면서 바깥 반복 수행
	--%>

		<% for(int j=1; j<=9; j++){ %>
		<%-- 
			안쪽 for문: 각 단마다 1~9까지 곱하는 수
			즉, i * j 형태로 구구단 출력
		--%>

			<p><%= i + "*" + j + "=" + (i*j) %></p>
			<%-- 
				<%= ... %> : 표현식
				→ 계산 결과를 HTML에 출력함
				예: 2*1=2, 2*2=4 ...
				→ 결과는 <p> 태그로 묶여서 줄마다 표시됨
			--%>

		<% } %>
	<% } %>

</body>
</html>
