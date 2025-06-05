
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDTO"%>
<%@page import="model2.mvcboard.MVCBoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MVCBoardDTO> boardLists = (List<MVCBoardDTO>)request.getAttribute("boardLists");
	Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판</title>
</head>
<body>
	<h2>목록 보기(List) - 현재 페이지 : <%=map.get("pageNum")%> (전체:<%=map.get("totalPage") %>)</h2>
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
			int countNum = 0;
			for(MVCBoardDTO dto : boardLists){
				int totalCount = Integer.parseInt(map.get("totalCount").toString());
				int pageNum = Integer.parseInt(map.get("pageNum").toString());
				int pageSize = Integer.parseInt(map.get("pageSize").toString());
				virtualNum = totalCount - ((pageNum - 1) * pageSize + countNum++);
				%>
				<tr align="center">
					<td><%=virtualNum%></td>
					<td align="left">
						<a href="View.jsp?num=<%=dto.getIdx() %>">
							<%=dto.getTitle()%>
						</a>
					</td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getVisitcount() %></td>
					<td><%=dto.getPostdate() %></td>
					<% if(dto.getOfile() != null){ %>
						<a href="../mvcboard/download.do?ofile=<%=dto.getOfile() %>&sfile=<%=dto.getSfile() %>&idx=<%=dto.getIdx()%>">[Down]</a>
					<%} %>
				</tr>
				
		<%}
		}%>
	</table>
	<table border="1" width="90%">
		<tr align="center">
			<td>
				<%= map.get("pagingImg") %>
			</td>
			<td>
				<button type="button" onClick="location.href='Write.jsp';">
					글쓰기
				</button>
			</td>
		</tr>
	</table>
</body>
</html>










