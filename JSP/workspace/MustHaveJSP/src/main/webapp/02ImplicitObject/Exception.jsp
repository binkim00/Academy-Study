<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - Exception</title>
</head>
<body>

	<%
		// response 내장 객체를 이용하여 현재 에러 상태코드 확인
		int status = response.getStatus();

		// 에러 상태에 따라 적절한 메시지를 출력
		if(status == 404){
			out.print("404에러가 발생했습니다. <br/>");
			out.print("경로를 확인해주세요.");
			// 다른 경로로 이동시키기
			/* response.sendRedirect("/MustHaveJSP/HelloJSP.jsp"); */
		}else if(status == 405){
			out.print("405에러가 발생했습니다. <br/>");
			out.print("요청 방식(method)을 확인해주세요.");
		}else if(status == 500){
			out.print("500에러가 발생했습니다. <br/>");
			out.print("소스코드에 오류가 없는지 확인해주세요.");
		}
	%>

</body>
</html>
