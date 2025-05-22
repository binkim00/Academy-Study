<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 게시물 개수와 게시물 데이터를 받아오기 위한 DAO 생성
	BoardDAO dao = new BoardDAO();
	// 검색 조건 및 검색어를 저장하는 Map을 생성
	Map<String, Object> param = new HashMap<>();
	// 제목이나 내용으로 검색할지 설정하는 데이터 searchField를 저장
	String searchField = request.getParameter("searchField");
	// 검색할 내용을 설정하는 데이터 searchWord를 저장
	String searchWord = request.getParameter("searchWord");
	// 검색어가 null이 아닐때만 map에 데이터를 저장
	if(searchWord != null && searchWord.length() > 0){
		param.put("searchField", searchField);
		param.put("searchWord", searchWord);
	}
	// 총 데이터 개수를 저장 - 총 페이지의 개수를 구하기 위하여
	int totalCount = dao.selectCount(param);
	// 모든 데이터를 List 저장
	List<BoardDTO> boardLists = dao.selectList(param);
	// 데이터베이스 접속을 종료
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>목록 보기(List)</h2>
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord"/>
					<input type="submit" value="검색하기"/>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" width="90%">
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
		</tr>
		<%if(boardLists.isEmpty()){ %>
			<tr>
				<td colspan="5" align="center">
					등록된 게시물이 없습니다^^*
				</td>
			</tr>
		<%}else{
			int virtualNum = 0;
			for(BoardDTO dto : boardLists){
				virtualNum = totalCount--;
				%>
				<tr align="center">
					<td><%=virtualNum%></td>
					<td align="left">
						<a href="View.jsp?num=<%=dto.getNum() %>">
							<%=dto.getTitle()%>
						</a>
					</td>
					<td><%=dto.getId() %></td>
					<td><%=dto.getVisitCount() %></td>
					<td><%=dto.getPostDate() %></td>
				</tr>
				
		<%}
		}%>
	</table>
	<table border="1" width="90%">
		<tr align="right">
			<td>
				<button type="button" onClick="location.href='Write.jsp';">
				글쓰기
				</button>
			</td>
		</tr>
	</table>
</body>
</html>










