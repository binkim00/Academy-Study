<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   <%-- [설정] JSP 파일의 문자 인코딩 설정 --%>

<%@ page import="common.Person" %>   <%-- [설정] 자바빈 클래스 사용을 위한 import --%>

<%
    // [★ 주요 개념 - include된 JSP는 같은 pageContext 사용]
    // 메인 JSP에서 저장한 데이터를 그대로 읽을 수 있음
    int pInteger2 = (Integer)(pageContext.getAttribute("pageInteger"));
    Person pPerson2 = (Person)(pageContext.getAttribute("pagePerson"));
%>

<ul>
	<li>Integer 객체 : <%=pInteger2 %></li>  <%-- [출력] 정수 출력 --%>
	<li>String 객체 : <%=pageContext.getAttribute("pageString") %></li>  <%-- [출력] 문자열 바로 꺼내서 출력 --%>
	<li>Person 객체 : <%=pPerson2.getName() %>, <%=pPerson2.getAge()%></li> <%-- [출력] 자바빈 getter 사용하여 출력 --%>
</ul>
