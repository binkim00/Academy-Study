<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - request</title>
</head>
<body>

	<h2>1. 클라이언트와 서버의 환경정보 읽기</h2>

	<!-- GET 방식: URL에 파라미터를 붙여서 전송 -->
	<a href="./RequestWebInfo.jsp?eng=Hello&han=안녕">Get 방식 전송</a>
	<br/>

	<!-- POST 방식: 폼으로 전송, URL에 파라미터가 안 보임 -->
	<form action="RequestWebInfo.jsp" method="post">
		영어 : <input type="text" name="eng" value="Bye"/> <br/>
		한글 : <input type="text" name="han" value="잘 가"/> <br/>
		<input type="submit" value="POST 전송 방식"/>
	</form>

	<h2>2. 클라이언트의 요청 매개변수 읽기</h2>

	<!-- POST 방식으로 다양한 입력 값 전달 -->
	<form method="post" action="RequestParameter.jsp">
		아이디 : <input type="text" name="id" value="" /><br/>

		성별 :
		<input type="radio" name="gender" value="man" />남자
		<input type="radio" name="gender" value="woman" checked="checked"/>여자
		<br/>

		관심사항 :
		<input type="checkbox" name="favo" value="eco" />경제
		<input type="checkbox" name="favo" value="pol" checked="checked"/>정치
		<input type="checkbox" name="favo" value="ent" />연예
		<br/>

		자기소개 :
		<textarea name="intro" cols="30" rows="4"></textarea>
		<br/>

		<input type="submit" value="전송하기" />
	</form>

	<h2>3. HTTP 요청 헤더 정보 읽기</h2>
	<a href="RequestHeader.jsp">요청 헤더 정보 읽기</a>

</body>
</html>
