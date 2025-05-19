<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//request.setCharacterEncoding("UTF-8");
String pValue = "방랑시인";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - param</title>
</head>
<body>
	<!-- scope : 어떤 영역에 저장할지 설정, request에서 바로 꺼내서 사용할 수 있음 -->
    <jsp:useBean id="person" class="common.Person" scope="request" />
    <jsp:setProperty name="person" property="name" value="김삿갓" /> 
    <jsp:setProperty name="person" property="age" value="56" /> 
    <!-- param1을 키로 김병연 value를 사용할 수 있음 -->
    <jsp:forward page="ParamForward.jsp?param1=김병연"> 
    	<!-- jsp:param도 request 데이터를 저장 -->
        <jsp:param name="param2" value="경기도 양주" />
        <jsp:param name="param3" value="<%=pValue%>" />
    </jsp:forward> 
</body>
</html>