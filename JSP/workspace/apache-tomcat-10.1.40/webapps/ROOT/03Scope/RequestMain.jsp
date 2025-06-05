<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person" %>

<%
	// [1] request 영역에 데이터 저장
	request.setAttribute("requestString", "request 영역의 문자열");  // 문자열 저장
	request.setAttribute("requestPerson", new Person("안중근", 31)); // 객체 저장
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 영역</title>
</head>
<body>
	<h2>request 영역의 속성값 삭제하기</h2>
	<%
		// [2] request 영역에서 특정 속성 제거
		request.removeAttribute("requestString");  // 이걸 지우면 다음에서 못 읽음
		request.removeAttribute("requestInteger"); // 존재하지 않아도 오류 없음
	%>

	<h2>request 영역의 속성값 읽기</h2>
	<%
		// [3] request 영역에서 Person 객체 꺼내오기
		Person rPerson = (Person)(request.getAttribute("requestPerson")); 
	%>
	<ul>
		<!-- [4] 문자열은 삭제했기 때문에 null 출력됨 -->
		<li>String객체 : <%=request.getAttribute("requestString") %></li>
		
		<!-- [5] Person 객체 정보 출력 (getName, getAge 메서드 활용) -->
		<li>Person객체 : <%=rPerson.getName() %>, <%=rPerson.getAge() %>
	</ul>

	<%
		// [6] 다른 JSP로 요청 전달 (forward)
		// 이때 request 영역의 값들은 그대로 전달된다!
		// forward의 경우 request의 데이터와 parameter 데ㅣ터를 전달할 수 있음
		request.getRequestDispatcher("RequestForward.jsp?han=한글&eng=영어")
			   .forward(request, response); 
		// sendRedirect의 경우 parameter 데이터만 전달할 수 있음/
		// response.sendRedirect("RequestForward.jsp?han=한글&eng=영어")
	%>
</body>
</html>
