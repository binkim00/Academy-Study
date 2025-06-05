<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
    page 지시어:
    - language: JSP가 사용하는 언어 (Java)
    - contentType: HTML + UTF-8로 출력하겠다는 의미
    - pageEncoding: JSP 파일 자체의 인코딩 설정
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 성적 표시하기</title>
<style>
	/* 테이블 테두리 및 정렬 스타일 설정 */
	table, td, th{
		border:1px solid black;
		border-collapse: collapse; /* 이중선 제거 */
	}
	th, td{
		width:100px;
		text-align: center; /* 가운데 정렬 */
	}
</style>
</head>
<body>
<!-- 
scores 배열의 점수 정보를 표 형태로 출력

표 형태:
학생번호    국어    영어    수학     총점     평균
=============================================
1번 학생:   80     60     70     210     70.0
2번 학생:   90     95     80     265     88.0
... 
-->
<h2>학생 성적 표 형태로 출력하기</h2>

<% 
	// 각 학생의 국어, 영어, 수학 점수를 담은 2차원 배열
	int[][] scores = {
		{80, 60, 70}, 
		{90, 95, 80}, 
		{75, 80, 100}, 
		{80, 70, 95}, 
		{100, 65, 80}
	}; 
%>

<table>
	<tr>
		<th>학생번호</th>
		<th>국어</th>
		<th>영어</th>
		<th>수학</th>
		<th>총점</th>
		<th>평균</th>
	</tr>

	<% 
		// 각 학생에 대해 반복문 실행
		for (int i = 0; i < scores.length; i++) {
			int sum = 0;  // 총점을 계산하기 위한 변수
			for (int j = 0; j < scores[i].length; j++) {
				sum += scores[i][j];  // 과목 점수를 더함
			}
			double avg = sum / 3.0;  // 평균 계산
	%>

	<tr>
		<td><%= (i + 1) %>번 학생</td>  <%-- 학생 번호 출력 --%>
		<td><%= scores[i][0] %></td>   <%-- 국어 점수 --%>
		<td><%= scores[i][1] %></td>   <%-- 영어 점수 --%>
		<td><%= scores[i][2] %></td>   <%-- 수학 점수 --%>
		<td><%= sum %></td>            <%-- 총점 --%>
		<td><%= String.format("%.1f", avg) %></td>  <%-- 평균 (소수 첫째 자리까지 출력) --%>
	</tr>

	<% } %>  <%-- for문 끝 --%>
</table>
</body>
</html>
