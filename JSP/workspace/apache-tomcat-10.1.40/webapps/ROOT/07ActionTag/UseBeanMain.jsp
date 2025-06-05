<%@ page import="common.Person" %> <%-- Person 클래스를 import --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
	<h2>useBean 액션 태그</h2>

	<h3>자바빈즈 생성하기</h3>
	<%-- 
		<jsp:useBean> 태그는 자바빈 객체를 생성하거나,
		이미 같은 범위(scope)에 존재하면 그 객체를 재사용합니다.
		여기서는 common.Person 클래스를 기반으로 "person"이라는 id로 객체를 생성합니다.
	--%>
	<jsp:useBean id="person" class="common.Person" scope="request" />

	<%-- 아래는 동일한 동작을 하는 자바 코드 (참고용) --%>
	<% // Person person = new Person(); %>

	<h3>setProperty 액션 태그로 자바빈즈 속성 지정하기</h3>
	<%-- 
		<jsp:setProperty>는 자바빈즈의 setter 메서드를 호출합니다.
		name="person"은 위에서 생성한 자바빈 id를 의미합니다.
		property="name"이면 setName("값") 메서드를 호출하는 것과 같습니다.
	--%>
	<jsp:setProperty name="person" property="name" value="임꺽정" />
	<% person.setName("임꺽정"); %> <%-- 동일 동작의 자바 코드 --%>

	<jsp:setProperty name="person" property="age" value="41" />
	<% person.setAge(41); %> <%-- 동일 동작의 자바 코드 --%>

	<h3>getProperty 액션 태그로 자바빈즈 속성 읽기</h3>
	<ul>
		<%-- 
			<jsp:getProperty>는 getter 메서드를 호출하여 값을 출력합니다.
			property="name"은 getName()의 결과를 HTML에 출력함을 의미합니다.
		--%>
		<li>이름 : <jsp:getProperty name="person" property="name"/></li>

		<%-- 아래는 값을 불러오긴 하지만 출력하지는 않음 --%>
		<li>이름 : <% person.getName(); %></li> <%-- 출력하려면 out.print(...) 필요 --%>

		<li>나이 : <jsp:getProperty name="person" property="age"/></li>
		<li>나이 : <% person.getAge(); %></li> <%-- 위와 동일하게 출력되지 않음 --%>
	</ul>
</body>
</html>
