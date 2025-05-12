<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   <%-- [★ JSP 설정] 자바 언어, UTF-8 인코딩 사용 설정 --%>

<%-- [★ JavaBean 연동] 자바빈 클래스 import → 사용 준비 --%>
<%@ page import="common.Person" %> 

<%
    // [★ 주요 개념 1 - pageContext 영역에 데이터 저장]
    // pageContext는 현재 JSP 페이지 내에서만 데이터를 공유함
    // page 영역에 데이터를 key, value형식으로 저장
    pageContext.setAttribute("pageInteger", 1000);        // 정수형 데이터 저장
    pageContext.setAttribute("pageString", "페이지 영역의 문자열");  // 문자열 저장
    pageContext.setAttribute("pagePerson", new Person("한석봉",99)); // 자바빈 객체 저장
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 영역 속성값 읽기</title>
</head>
<body>

	<h2>page 영역 속성값 읽기</h2>
	<%
	    // [★ 주요 개념 2 - pageContext에서 값 읽기]
	    // getAttribute(key) : page 영엑에 맞는 key 맞는 데이터를 반환
	    // 저장한 객체를 꺼낼 때는 형변환(casting)이 꼭 필요함, 캐스팅을 해야 변수에 저장 가능
	    int pInteger = (Integer)(pageContext.getAttribute("pageInteger"));
	    String pString = (String)(pageContext.getAttribute("pageString"));
	    Person pPerson = (Person)(pageContext.getAttribute("pagePerson"));
	%>

	<ul>
		<li>Integer 객체 : <%=pInteger %></li>   <%-- [출력] 정수 데이터 출력 --%>
		<li>String 객체 : <%=pString %></li>     <%-- [출력] 문자열 출력 --%>
		 <%-- [★ 주요 개념 3 - JavaBean getter 사용] --%>
		<li>Person 객체 : <%=pPerson.getName() %>, <%=pPerson.getAge()%></li>
	</ul>

	<h2>include된 파일에서 page 영역 읽어오기</h2>
	<%-- [★ 주요 개념 4 - include는 pageContext 공유 가능] --%>
	<!-- page 영엑에 저장한 데이터는 include한 페이지에서도 사용할 수 있음 -->
	<%@ include file="PageInclude.jsp" %>  

	<h2>페이지 이동 후 page 영역 읽어오기</h2>
	 <%-- [★ 주요 개념 5 - 페이지 이동은 공유 안 됨] → 새로운 pageContext 생성됨 --%>
	<a href="PageLocation.jsp">PageLocation.jsp</a> 
</body>
</html>
