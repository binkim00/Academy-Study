<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    buffer="1kb" 
    autoFlush="true" %>
<!-- buffer : 한번에 전송가능한 용량 설정 기본값:8kb -->
<!-- autoFlush : 버퍼가 가득차면 데이터를 전송하고 버퍼를 자동으로 비우는 옵션 기본값:true-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buffer, autoFlush 테스트</title>
</head>
<body>
	<%
		for(int i=1; i<=100; i++){
			out.println("abcde12345"); // 버퍼에 계속 출력
		}
	%>
</body>
</html>




