<%@page import="common.DBConnPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board 데이터 추가하기</title>
</head>
<body>
<h2>게시판 추가 테스트</h2>
	<% DBConnPool jdbc = new DBConnPool();
	String title = "제목입니다.";
	String content = "내용입니다.";
	String id = "musthave";
	
	String sql = "INSERT INTO board(num, title,content,id) VALUES (SEQ_BOARD_NUM.NEXTVAL,?,?,?)";
	jdbc.psmt = jdbc.con.prepareStatement(sql);
	jdbc.psmt.setString(1, title);
	jdbc.psmt.setString(2, content);
	jdbc.psmt.setString(3, id);
	int inResult = jdbc.psmt.executeUpdate();
	out.print(inResult + "행이 입력되었습니다.");
	jdbc.close();
	%>
</body>
</html>








