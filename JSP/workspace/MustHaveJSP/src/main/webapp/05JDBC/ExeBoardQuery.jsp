<%@page import="java.util.Date"%>
<%@page import="common.DBConnPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board 데이터 출력하기</title>
</head>
<style>
	table, td, th{
		border : 1px solid black;
		border-collapse: collapse;
	}
</style>
<body>
<h2>게시판 목록 조회</h2>
	<%
		DBConnPool jdbc = new DBConnPool();
		String sql = "SELECT * FROM board";
		jdbc.psmt = jdbc.con.prepareStatement(sql);
		jdbc.rs = jdbc.psmt.executeQuery();
	%>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<% while(jdbc.rs.next()){%>	
		<tr>
			<td><%=jdbc.rs.getInt("num") %></td>
			<td><%=jdbc.rs.getString("title") %></td>
			<td><%=jdbc.rs.getString("content") %></td>
			<td><%=jdbc.rs.getString("id") %></td>
			<td><%=jdbc.rs.getDate("postdate") %></td>
			<td><%=jdbc.rs.getInt("visitcount") %></td>
		</tr>
		<%} %>
	</table>
	<%jdbc.close();%>
</body>
</html>




