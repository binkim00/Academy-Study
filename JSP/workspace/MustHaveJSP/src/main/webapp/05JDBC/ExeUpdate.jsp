<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가 테스트</title>
</head>
<body>
	<h2>회원 추가 테스트</h2>
	<%JDBConnect jdbc = new JDBConnect();
	String id = "test1";
	String pass = "1111";
	String name = "테스트1회원";
	
	//SQL문 작성 부분 : 쿼리문 안의 ?는 변수를 설정할 수 있음
	String sql = "INSERT INTO member VALUES (?,?,?,sysdate)";
	// PrepareStatement객체 생성 : 생성할때 실행할 SQL문을 넣어야 합니다.
	jdbc.psmt = jdbc.con.prepareStatement(sql);
	// setString(물음표의 위치값, 물음표에 대입할 데이터) : SQL문의 ?에 데이터 설정
	jdbc.psmt.setString(1, id);
	jdbc.psmt.setString(2, pass);
	jdbc.psmt.setString(3, name);
	// INSERT INTO member VALUES ('test1','1111','테스트1회원',sysdate);
	// psmt.executeUpdate() : 위에서 설정한 SQL문을 실행하는 메서드
	// INSERT, UPDATE, DELETE 만 실행해야함
	// psmt.excuteQuery() : SELECT을 실행하는 메서드
	int inResult = jdbc.psmt.executeUpdate();
	out.print(inResult + "행이 입력되었습니다.");
	jdbc.close();
	%>
</body>
</html>









