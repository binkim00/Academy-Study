<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index 페이지</title>
</head>
<body>
	<jsp:include page="/Common/Link.jsp" />
	<h1>index 페이지</h1>
	<p><a href="./08Board/List.jsp">게시판(페이징X)</a></p>
	<p><a href="./09PagingBoard/List.jsp">게시판(페이징O)</a></p>
	<p><a href="./13FileUpload/FileList.jsp">파일 게시판</a></p>
	<p><a href="./14MVCBoard/list2.do">MVC게시판</a></p>
</body>
</html>




