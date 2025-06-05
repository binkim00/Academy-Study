<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.time.LocalDate, java.time.LocalDateTime" %>
<!-- 특징
실행할 때 별도로 IncludeFile.jsp를 실행해서 결과만 포함
변수, 선언부는 공유되지 않음
실행 시점의 동적 결과를 반영할 수 있음
실시간으로 바뀌는 데이터(날짜, 로그인 상태 등)에 유리함 -->
<%
	LocalDate toDay = LocalDate.now();
	LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
%>
