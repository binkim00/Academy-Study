<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
  ▶ JSP 페이지 설정
  - language="java": 사용할 언어는 Java
  - contentType="text/html; charset=UTF-8": 브라우저에 HTML 문서를 UTF-8로 보냄
  - pageEncoding="UTF-8": 이 JSP 파일 자체의 문자 인코딩 설정
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 브라우저가 페이지를 UTF-8로 해석하도록 설정 -->
<title>테이블 태그로 출력하는 구구단</title>

<style>
	/* CSS로 테이블 스타일 지정 */
	table, td {
		border: 1px solid black;        /* 테이블 셀마다 검정 테두리 */
		border-collapse: collapse;      /* 이중 테두리를 하나로 합침 */
		padding: 5px;                   /* 셀 안쪽 여백 */
		text-align: center;             /* 셀 안의 텍스트를 가운데 정렬 */
	}
</style>
</head>

<body>

<h2>구구단 출력 (2단 ~ 9단)</h2> <!-- 페이지 제목 출력 -->

<table> <!-- 구구단 전체를 테이블로 출력 시작 -->

	<tr> <!-- 첫 번째 행: 2단부터 9단까지 제목 셀 출력 -->
		<% for (int dan = 2; dan <= 9; dan++) { %>
			<td><strong><%= dan %>단</strong></td>
			<%-- 
			  - 반복문을 통해 2~9단까지 제목을 출력
			  - <%= dan %>은 JSP 표현식: dan 값을 HTML로 출력함
			  - 예: <td><strong>2단</strong></td>
			--%>
		<% } %>
	</tr>

	<% 
		// 바깥쪽 for문: 각 곱해지는 수 i (1~9) 순서대로 줄을 생성
		for (int i = 1; i <= 9; i++) { 
	%>
	<tr> <!-- 각 줄마다 새로운 행 시작 -->
		<% 
			// 안쪽 for문: 각 단(dan)마다 곱셈 결과 출력
			for (int dan = 2; dan <= 9; dan++) { 
		%>
			<td>
				<%= dan %> x <%= i %> = <%= dan * i %>
			</td>
			<%-- 
			  예: 
			  - 2 x 1 = 2 
			  - <%= dan * i %> : JSP가 계산한 결과를 출력
			  - 결과적으로 한 줄에 2단부터 9단까지 같은 곱해지는 수(i)에 대한 결과가 나옴
			--%>
		<% } %>
	</tr> <!-- 하나의 행이 끝남 (1단계 곱셈 끝) -->
	<% } %> <!-- 바깥쪽 for문 끝 (1~9까지 반복) -->

</table> <!-- 전체 테이블 종료 -->

</body>
</html>
